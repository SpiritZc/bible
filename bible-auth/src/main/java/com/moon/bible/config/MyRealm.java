package com.moon.bible.config;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.moon.bible.api.sysfunction.ISysFunctionService;
import com.moon.bible.api.sysrole.ISysRoleService;
import com.moon.bible.api.sysuser.ISysUserService;
import com.moon.bible.entity.sysfunction.SysFunction;
import com.moon.bible.entity.sysuser.SysUser;
import com.moon.bible.base.UserInfoDto;
import com.moon.bible.constants.StatusCode;
import com.moon.bible.enums.DelFlagEnum;
import com.moon.bible.enums.FunctionTypeEnum;
import com.moon.bible.enums.LoginTypeEnum;
import com.moon.bible.enums.YesNoEnum;
import com.moon.bible.exception.TokenExpiredException;
import com.moon.bible.util.DateUtil;
import com.moon.bible.util.JWTUtil;
import com.moon.bible.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.subject.WebSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


/**
 * @ClassName: MyRealm
 * @Description:
 * @Author 蔡阳
 * @DateTime 2020年5月31日12:01:59
 */

public class MyRealm extends AuthorizingRealm {

	@Autowired
	@Lazy
	private ISysFunctionService iSysFunctionService;

	@Autowired
	@Lazy
	private ISysRoleService iSysRoleService;
	
	@Autowired
	@Lazy
	private ISysUserService iSysUserService;
	
	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof JWTToken;
	}

	/**
	 *	权限验证
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		UserInfoDto userInfoDto = JWTUtil.getUserInfo(principals.toString());
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		if (YesNoEnum.YES.getCode()!=userInfoDto.getIsAdmin()) {
			//非管理员
			List<SysFunction> sysFunctions = iSysFunctionService.getFunctionsByRole(userInfoDto.getRoleId());
			//获取用户相应的permission  
	        List<String> permissions = new ArrayList<String>();	
	        if (sysFunctions != null && sysFunctions.size() > 0) {
	        	for (int i = 0; i < sysFunctions.size(); i++) {
		        	permissions.add(sysFunctions.get(i).getFunctionCode());
		        }
			}
	        info.addStringPermissions(permissions);
		}else {
			//管理员
			List<String> permissions = new ArrayList<String>();
			List<SysFunction> functions = iSysFunctionService.getAllFunctions();
			if (functions != null && functions.size() > 0) {
				for (int i = 0; i < functions.size(); i++) {
					permissions.add(functions.get(i).getFunctionCode());
				}
			}
			info.addStringPermissions(permissions);
		}
		
		return info;
	}

	/**
	 * 默认使用此方法进行用户正确与否验证，错误抛出异常即可
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		JWTToken jwtToken = (JWTToken) authenticationToken;
		String token = jwtToken.getToken();
		Integer type = jwtToken.getType();
		//后台登录
		if (LoginTypeEnum.ADMIN.getCode().equals(type)) {
			// 解密获得username，用于和数据库进行对比
			UserInfoDto userInfoDto = JWTUtil.getUserInfo(token);
			if (StringUtil.isNullOrEmpty(userInfoDto.getAccountName())) {
				throw new AuthenticationException("token 无效！");
			}
			QueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>();
			queryWrapper.eq("account_name", userInfoDto.getAccountName());
			queryWrapper.eq("del_flag", DelFlagEnum.UNDEL.getCode());
			SysUser userAccount = iSysUserService.getOne(queryWrapper);
			if (userAccount == null) {
				throw new AuthenticationException("用户"+userInfoDto.getUserName()+"不存在") ;
			}
			String statusCode = JWTUtil.verify(token, userInfoDto, userAccount.getPassword());//token校验返回码
			if (StatusCode.TOKEN_FAILURE.equals(statusCode)) {
				//token失效，并且token在正常失效范围内，则可以正常访问，否则不允许访问
				 //判断过期时间是否超过两个小时，超过两个小时，则token失效，两个小时之内允许访问并刷新token
                long now = DateUtil.getTimestampLong();//获取当前时间
                long expireDate = userInfoDto.getExpireDate().getTime();//获取过期时间
                if ((now - expireDate)<JWTUtil.PC_ADMIN_REFRESH_TIME) {
                	ServletResponse response = ((WebSubject)SecurityUtils.getSubject()).getServletResponse(); 
                	String newToken = JWTUtil.sign(userInfoDto, userAccount.getPassword());
                	HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        	        httpServletResponse.setHeader("Authorization", newToken);//刷新token
                	return new SimpleAuthenticationInfo(token, token, "my_realm");
                }else {
                	ServletResponse response = ((WebSubject)SecurityUtils.getSubject()).getServletResponse();
                	HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                	httpServletResponse.setHeader("statusCode", StatusCode.TOKEN_FAILURE);
                	throw new TokenExpiredException("token过期，请重新登录!");
                }
				
			}else if (StatusCode.TOKEN_ERROR.equals(statusCode)) {
				//token错误
				throw new AuthenticationException("账户密码错误!");
			}
			
			return new SimpleAuthenticationInfo(token, token, "my_realm");
		}else {
			// 解密获得username，用于和数据库进行对比
			UserInfoDto userInfoDto = JWTUtil.getUserInfo(token);
			if (StringUtil.isNullOrEmpty(userInfoDto.getPhoneNo())) {
				throw new AuthenticationException("token 无效！");
			}
			QueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>();
			queryWrapper.eq("user_phone", userInfoDto.getPhoneNo());
			queryWrapper.eq("del_flag", DelFlagEnum.UNDEL.getCode());
			SysUser userAccount = iSysUserService.getOne(queryWrapper);
			if (userAccount == null) {
				throw new AuthenticationException("用户"+userInfoDto.getPhoneNo()+"不存在") ;
			}
			String statusCode = JWTUtil.verify(token, userInfoDto, userAccount.getUserPhone());//token校验返回码
			if (StatusCode.TOKEN_FAILURE.equals(statusCode)) {
				//token失效，并且token在正常失效范围内，则可以正常访问，否则不允许访问
				//判断过期时间是否超过两个小时，超过两个小时，则token失效，两个小时之内允许访问并刷新token
				long now = DateUtil.getTimestampLong();//获取当前时间
				long expireDate = userInfoDto.getExpireDate().getTime();//获取过期时间
				if ((now - expireDate)<JWTUtil.PC_ADMIN_REFRESH_TIME) {
					ServletResponse response = ((WebSubject)SecurityUtils.getSubject()).getServletResponse();
					String newToken = JWTUtil.sign(userInfoDto, userAccount.getUserPhone());
					HttpServletResponse httpServletResponse = (HttpServletResponse) response;
					httpServletResponse.setHeader("Authorization", newToken);//刷新token
					return new SimpleAuthenticationInfo(token, token, "my_realm");
				}else {
					ServletResponse response = ((WebSubject)SecurityUtils.getSubject()).getServletResponse();
					HttpServletResponse httpServletResponse = (HttpServletResponse) response;
					httpServletResponse.setHeader("statusCode", StatusCode.TOKEN_FAILURE);
					throw new TokenExpiredException("token过期，请重新登录!");
				}

			}else if (StatusCode.TOKEN_ERROR.equals(statusCode)) {
				//token错误
				throw new AuthenticationException("账户密码错误!");
			}
			return new SimpleAuthenticationInfo(token, token, "my_realm");
		}
		
	}

}
