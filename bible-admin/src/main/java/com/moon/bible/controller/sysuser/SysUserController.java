/** 
 * 模块：bible-SysUser
 */
package com.moon.bible.controller.sysuser;

import java.util.List;

import com.moon.bible.annotation.LoginUser;
import com.moon.bible.annotation.NoRepeatSubmit;
import com.moon.bible.base.UserInfoDto;
import com.moon.bible.dto.sysuser.UserRoleDto;
import com.moon.bible.dto.sysuser.SysUseDto;
import com.moon.bible.dto.sysuser.UserWithRoleDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.moon.bible.base.BaseController;
import com.moon.bible.base.BaseEntity;
import com.moon.bible.constants.Constants;
import com.moon.bible.entity.sysuser.SysUser;
import com.moon.bible.annotation.Check;
import com.moon.bible.annotation.MethodLog;
import com.moon.bible.api.sysuser.ISysUserService;
import io.swagger.annotations.ApiOperation;
 /**  
* @Description: SysUsercontroller类
* @author 
* @date 2020-07-14 07:28:39
* @version V1.0  
 */
@RestController
@RequestMapping("/api/sysUser")
public class SysUserController extends BaseController {

	/**
	 * iSysUserService服务注入
	 */
	@Autowired
	private ISysUserService iSysUserService;

	/** 
	* @Description: 分页查询表格
	* @param SysUser
	* @param @return  
	* @return BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="SysUser-获取表格数据", notes="获取表格数据")
	@RequestMapping(value = "/getTableList",method = RequestMethod.POST)
	public BaseEntity getTableList(@RequestBody SysUser model)
	{
		
		BaseEntity result = new BaseEntity();
		result = iSysUserService.tablePagingQueryByAuthority(model);
		return result;
	}

	/** 
	* @Description: 获取详细信息
	* @param id
	* @param @return  BaseEntity
	* @return  
	* @throws 
	*/ 
	@ApiOperation(value="SysUser-获取详细信息", notes="根据id获取详细信息")
	@RequestMapping(value = "/getDetail",method = RequestMethod.GET)
	@Check({"id:required#主键ID"})
	public BaseEntity getDetail(@RequestParam Long id) throws Exception
	{
		BaseEntity result = iSysUserService.getDetail(id);
		return result;
	}

	/**
	* @throws Exception  
	* @Description: 新增
	* @param SysUser
	* @return BaseEntity 
	* @throws 
	*/ 
	@ApiOperation(value="SysUser-新增数据", notes="新增数据")
	@RequestMapping(value = "/insert",method = RequestMethod.POST)
	@MethodLog(module="SysUser",remark="新增",operateType=Constants.OPERATE_TYPE_ADD)
	@Check({"userName:required#用户名;length#用户名#40","accountName:required#账号名(唯一);length#账号名(唯一)#40","userPhone:required#手机号;length#手机号#11;mobile","userEmail:required#邮箱;length#邮箱#50;email",})

	public BaseEntity insert(@RequestBody UserWithRoleDto model, @LoginUser UserInfoDto userInfoDto) throws Exception
	{
		BaseEntity result = iSysUserService.insert(model);
		return result;
	}

	/**
	* @throws Exception  
	* @Description: 更新
	* @param SysUser
	* @return  BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="SysUser-更新数据", notes="更新数据")
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	@MethodLog(module="SysUser",remark="更新",operateType=Constants.OPERATE_TYPE_UPDATE)
	@Check({"id:required#主键ID","userName:required#用户名;length#用户名#40","accountName:required#账号名(唯一);length#账号名(唯一)#40","userPhone:required#手机号;length#手机号#11;mobile","userEmail:required#邮箱;length#邮箱#50;email",})
	public BaseEntity update(@RequestBody UserWithRoleDto model) throws Exception
	{
		BaseEntity result = iSysUserService.update(model);
		return result;
	}

	/** 
	* @Description: 单条删除
	* @param id
	* @return  BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="SysUser-单条删除数据", notes="根据主键删除数据")
	@RequestMapping(value = "/delete",method = RequestMethod.GET)
	@MethodLog(module="SysUser",remark="单条删除",operateType=Constants.OPERATE_TYPE_DELETE)
	@Check({"id:required#主键ID"})
	public BaseEntity delete(@RequestParam Long id)
	{
		BaseEntity result = iSysUserService.delete(id);
		return result;
	}

	/** 
	* @Description: 批量删除
	* @param SysUser 
	* @return BaseEntity 
	* @throws 
	*/ 
	@ApiOperation(value="SysUser-批量删除", notes="批量删除数据")
	@RequestMapping(value = "/deletebatch",method = RequestMethod.POST)
	@MethodLog(module="SysUser",remark="批量删除",operateType=Constants.OPERATE_TYPE_BATCHDELETE)
	public BaseEntity deletebatch(@RequestBody List<Long> ids)
	{
		BaseEntity result = iSysUserService.deleteBatch(ids);
		return result;
	}

	 /**
	  * @Method resetPwd
	  * @Author zhangcheng
	  * @Version  1.0
	  * @Description 根据用户id重置密码
	  * @Return com.baiyyy.yfz.base.BaseEntity
	  * @Exception
	  * @Date 2020-6-12 9:13
	  */
	 @ApiOperation(value="UserAccount-重置密码", notes="重置密码")
	 @RequestMapping(value = "/resetPwd",method = RequestMethod.GET)
	 @MethodLog(module="UserAccount",remark="重置密码",operateType=Constants.OPERATE_TYPE_UPDATE)
	 @Check({"id:required#主键ID"})
	 public BaseEntity resetPwd(@RequestParam Long id){
		 BaseEntity result = iSysUserService.resetPwd(id);
		 return result;
	 }


	 /**
	  * @Title: changePwd
	  * @Description: 修改密码
	  * @param sysUseDto
	  * @return
	  * @author caiyang
	  * @date 2020-06-12 01:20:47
	  */
	 @ApiOperation(value="UserAccount-修改密码", notes="修改密码")
	 @RequestMapping(value = "/changePwd",method = RequestMethod.POST)
	 @MethodLog(module="UserAccount",remark="修改密码",operateType=Constants.OPERATE_TYPE_UPDATE)
	 @Check({"oldPwd:required#旧密码","newPwd:required#新密码"})
	 public BaseEntity changePwd(@RequestBody SysUseDto sysUseDto, @LoginUser UserInfoDto userInfoDto) {
		 return iSysUserService.changePwd(sysUseDto, userInfoDto);
	 }

	 /**
	  * @Title: updateStatus
	  * @Description: 账号启用停用
	  * @param userAccount
	  * @return
	  * @author caiyang
	  * @date 2020-06-23 07:48:08
	  */
	 @ApiOperation(value="UserAccount-启用停用", notes="启用停用")
	 @RequestMapping(value = "/updateStatus",method = RequestMethod.POST)
	 @MethodLog(module="UserAccount",remark="启用停用",operateType=Constants.OPERATE_TYPE_UPDATE)
	 @Check({"id:required#主键id","status:required#状态"})
	 @NoRepeatSubmit
	 public BaseEntity updateStatus(@RequestBody SysUser sysUser) {

		 return this.iSysUserService.updateStatus(sysUser);
	 }

	 @ApiOperation(value="UserAccount-根据角色id查询用户列表", notes="根据角色id查询用户列表")
	 @RequestMapping(value = "/getUserByRoleId",method = RequestMethod.GET)
	 public List<SysUser> getUserByRoleId(String roleId){
		 List<SysUser> users = iSysUserService.getUserByRoleId(roleId);
		 return users;
	 }
}
