package com.moon.bible.impl.login;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.moon.bible.api.login.ILoginService;
import com.moon.bible.api.sysfunction.ISysFunctionService;
import com.moon.bible.api.sysrole.ISysRoleService;
import com.moon.bible.api.sysuser.ISysUserService;
import com.moon.bible.api.userloginlog.IUserLoginLogService;
import com.moon.bible.constants.StatusCode;
import com.moon.bible.dto.sysuser.UserLoginDto;
import com.moon.bible.entity.sysfunction.SysFunction;
import com.moon.bible.entity.sysrole.SysRole;
import com.moon.bible.entity.sysuser.SysUser;
import com.moon.bible.entity.userloginlog.UserLoginLog;
import com.moon.bible.base.UserInfoDto;
import com.moon.bible.enums.DelFlagEnum;
import com.moon.bible.enums.LoginTypeEnum;
import com.moon.bible.enums.UserStatusEnum;
import com.moon.bible.enums.YesNoEnum;
import com.moon.bible.exception.BizException;
import com.moon.bible.mapper.sysuser.SysUserMapper;
import com.moon.bible.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName : LoginServiceImpl  //类名
 * @Description : 登录用服务接口  //描述
 * @Author : HTB  //作者
 * @Date: 2020-07-18 09:25  //时间
 */
@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private ISysUserService iSysUserService;

    @Autowired
    private IUserLoginLogService iUserLoginLogService;

    @Autowired
    private ISysRoleService iSysRoleService;

    @Autowired
    private ISysFunctionService iSysFunctionService;

    @Value("${wx.appid}")
    private String appid;

    @Value("${wx.appsecret}")
    private String secret;

    @Value("${wx.requestUrl}")
    private String requestUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 功能描述: 平台用户登录 <br>
     * 〈〉
     * @Param: [sysUser]
     * @Return: UserInfoDto
     * @Author: Administrator
     * @Date: 2020/7/18 9:26
     */
    @Override
    public UserInfoDto doLogin(UserLoginDto sysUser)throws Exception {
        long startTime=System.currentTimeMillis();   //获取开始时间
        UserLoginLog userLoginLog = new UserLoginLog();//用户登录日志
        userLoginLog.setAccountName(sysUser.getAccountName());
        userLoginLog.setOperateMethod("com.moon.bible.impl.login.LoginServiceImpl.doLogin(UserAccount)");
        userLoginLog.setOperateParams(JSONObject.toJSONString(sysUser));
        userLoginLog.setOperateTime(new Date());
        userLoginLog.setOperateIp(CusAccessObjectUtil.getIpAddress(httpServletRequest));
        //返回结果
        UserInfoDto result = new UserInfoDto();
        //token
        String token = "";
        //判断登录方式
        if (sysUser.getLoginType() == LoginTypeEnum.ADMIN.getCode()){
            //后续可能判断分别是什么设备,判断是否密码登录还是验证码登录
            //根据用户输入的账号查询账号信息
            QueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>();
            queryWrapper.eq("account_name", sysUser.getAccountName());
            queryWrapper.eq("del_flag", DelFlagEnum.UNDEL.getCode());
            SysUser user = iSysUserService.getOne(queryWrapper);
            if (user == null) {//用户不存在
                long endTime = System.currentTimeMillis();   //获取结束时间
                userLoginLog.setLogStatus(YesNoEnum.NO.getCode());//登录失败
                userLoginLog.setExecuteTime(String.valueOf((endTime - startTime)));
                userLoginLog.setErrorInfo(MessageUtil.getValue("error.user.notexist"));
                iUserLoginLogService.insert(userLoginLog);
                throw new BizException(StatusCode.LOGIN_FAILURE, MessageUtil.getValue("error.user.notexist"));
            }
            if (UserStatusEnum.NO.getCode() == user.getStatus()) {//用户停用
                long endTime = System.currentTimeMillis();   //获取结束时间
                userLoginLog.setLogStatus(YesNoEnum.NO.getCode());//登录失败
                userLoginLog.setExecuteTime(String.valueOf((endTime - startTime)));
                userLoginLog.setErrorInfo(MessageUtil.getValue("error.user.stop"));
                iUserLoginLogService.insert(userLoginLog);
                throw new BizException(StatusCode.LOGIN_FAILURE, MessageUtil.getValue("error.user.stop"));
            }

            //对比密码
            if (Md5Util.generateMd5(sysUser.getPassword()).equals(user.getPassword())) {
                result.setUserId(user.getId());
                result.setAccountName(user.getAccountName());
                result.setUserName(user.getUserName());
                result.setIsAdmin(user.getIsAdmin());
                result.setPhoneNo(user.getUserPhone());
                //非超级管理员角色需获取角色信息和组织信息
                if (YesNoEnum.YES.getCode() != user.getIsAdmin()) {
                    //获取用户角色信息
                    SysRole userRole = iSysRoleService.getUserRole(user.getId());
                    if (userRole == null) {
                        throw new BizException(StatusCode.LOGIN_FAILURE, MessageUtil.getValue("error.norole"));
                    }
                    result.setRoleId(userRole.getId());
                    result.setRoleName(userRole.getRoleName());
                    result.setRoleCode(userRole.getRoleCode());
                    //获取用户权限信息
                    List<SysFunction> promissionByRole =
                            iSysFunctionService.getFunctionsByRole(userRole.getId());
                    List<String> collect =
                            promissionByRole.stream().map(p -> p.getFunctionCode()).collect(Collectors.toList());
                    result.setFunctions(collect);

                }else{
                    //获取所有的权限信息
                    QueryWrapper<SysFunction> queryWrapper1 = new QueryWrapper<SysFunction>();
                    queryWrapper1.eq("del_flag", DelFlagEnum.UNDEL.getCode());
                    List<SysFunction> list = iSysFunctionService.list(queryWrapper1);
                    List<String> collect =
                            list.stream().map(p -> p.getFunctionCode()).collect(Collectors.toList());
                    result.setFunctions(collect);
                }
            }else {
                long endTime = System.currentTimeMillis();   //获取结束时间
                userLoginLog.setLogStatus(YesNoEnum.NO.getCode());//登录失败
                userLoginLog.setExecuteTime(String.valueOf((endTime - startTime)));
                userLoginLog.setErrorInfo(MessageUtil.getValue("error.login"));
                iUserLoginLogService.insert(userLoginLog);
                throw new BizException(StatusCode.LOGIN_FAILURE, MessageUtil.getValue("error.login"));
            }
            //生成token
            token = JWTUtil.sign(result, user.getPassword());
        }else {
            //微信端
            //小程序登录，获取小程序appid
            Map<String, String> requestUrlParam = new HashMap<>();
            requestUrlParam.put("appid", this.appid);
            //小程序secret
            requestUrlParam.put("secret", this.secret);
            //小程序端返回的code
            requestUrlParam.put("js_code", sysUser.getJscode());
            //默认参数
            requestUrlParam.put("grant_type", "authorization_code");

            String resultsString = restTemplate.getForEntity(requestUrl+"?appid={appid}&secret={secret}&js_code={js_code}&grant_type={grant_type}", String.class,requestUrlParam).getBody();
            //解析相应内容（转换成json对象）
            JSONObject json = JSONObject.parseObject(resultsString);
            String errcode = json.getString("errcode");
            if (StringUtil.isNullOrEmpty(errcode)) {
                //获取会话密钥（session_key）
                String session_key = json.get("session_key").toString();
                //用户的唯一标识（openid）
                String openid = (String) json.get("openid");
                //查询数据库的openid,如果存在就直接生成token;
                QueryWrapper<SysUser> queryWrapperOp = new QueryWrapper<>();
                queryWrapperOp.eq("open_id",openid);
                queryWrapperOp.eq("del_flag",DelFlagEnum.UNDEL.getCode());
                SysUser sysUser1 = sysUserMapper.selectOne(queryWrapperOp);
                String phoneNo = "";
                if (null==sysUser1) {//如果用户不存在
                    // 2、对encryptedData加密数据进行AES解密
                    try {
                        String resultInfo = AesCbcUtil.decrypt(sysUser.getEncryptedData(), session_key, sysUser.getIv(), "UTF-8");
                        if (null != resultInfo && resultInfo.length() > 0) {
                            JSONObject userInfoJSON = JSONObject.parseObject(resultInfo);
                            phoneNo = userInfoJSON.getString("phoneNumber");
                            //查询是否有这个用户
                            QueryWrapper<SysUser> queryWrapper = new QueryWrapper();
                            queryWrapper.eq("user_phone", phoneNo);
                            queryWrapper.eq("del_flag", DelFlagEnum.UNDEL.getCode());
                            SysUser one = iSysUserService.getOne(queryWrapper);
                            //如果用户不存在.创建新用户
                            if (null == one) {
                                SysUser sysUserNew = new SysUser();
                                sysUserNew.setAccountName(phoneNo);
                                sysUserNew.setUserPhone(phoneNo);
                                sysUserNew.setUserName(phoneNo);
                                sysUserNew.setLastLoginTime(new Date());
                                sysUserNew.setOpenId(openid);
                                iSysUserService.save(sysUserNew);
                                result.setUserId(sysUserNew.getId());
                                result.setAccountName(sysUserNew.getAccountName());
                                result.setUserName(sysUserNew.getUserName());
                                result.setIsAdmin(sysUserNew.getIsAdmin());
                                result.setPhoneNo(sysUserNew.getUserPhone());
                            } else {
                                //否则更新用户openId
                                one.setOpenId(openid);
                                sysUserMapper.updateById(one);
                                result.setUserId(one.getId());
                                result.setAccountName(one.getAccountName());
                                result.setUserName(one.getUserName());
                                result.setIsAdmin(one.getIsAdmin());
                                result.setPhoneNo(one.getUserPhone());
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        long endTime = System.currentTimeMillis();   //获取结束时间
                        userLoginLog.setLogStatus(YesNoEnum.NO.getCode());//登录失败
                        userLoginLog.setExecuteTime(String.valueOf((endTime - startTime)));
                        userLoginLog.setErrorInfo(MessageUtil.getValue("error.wx.login"));
                        iUserLoginLogService.insert(userLoginLog);
                        throw new BizException(StatusCode.LOGIN_FAILURE, MessageUtil.getValue("error.wx.login"));
                    }
                }else {
                    result.setUserId(sysUser1.getId());
                    result.setAccountName(sysUser1.getAccountName());
                    result.setUserName(sysUser1.getUserName());
                    result.setIsAdmin(sysUser1.getIsAdmin());
                    result.setPhoneNo(sysUser1.getUserPhone());
                    phoneNo = sysUser1.getUserPhone();
                }
                //生成token
                token = JWTUtil.sign(result, phoneNo);
            }else {
                throw new BizException(StatusCode.LOGIN_FAILURE, MessageUtil.getValue("error.wx.parsing"));
            }

        }
        result.setToken(token);
        long endTime = System.currentTimeMillis();   //获取结束时间
        userLoginLog.setLogStatus(YesNoEnum.YES.getCode());//登录成功
        userLoginLog.setExecuteTime(String.valueOf((endTime - startTime)));
        userLoginLog.setResult(JSONObject.toJSONString(result));
        iUserLoginLogService.insert(userLoginLog);
        return result;
    }

    public static void main(String[] args) {
//        String resultInfo = AesCbcUtil.decrypt(,"RTfDIJKAXw\\/J2SW0xDk38w==", , "UTF-8");
    }
}
