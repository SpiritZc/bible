/** 
 * 模块：bible-SysRole
 */
package com.moon.bible.controller.sysrole;

import java.util.List;

import com.moon.bible.annotation.LoginUser;
import com.moon.bible.base.UserInfoDto;
import com.moon.bible.dto.sysrole.UserRoleAuthDto;
import com.moon.bible.dto.sysuser.UserRoleDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.moon.bible.base.BaseController;
import com.moon.bible.base.BaseEntity;
import com.moon.bible.constants.Constants;
import com.moon.bible.entity.sysrole.SysRole;
import com.moon.bible.annotation.Check;
import com.moon.bible.annotation.MethodLog;
import com.moon.bible.api.sysrole.ISysRoleService;
import io.swagger.annotations.ApiOperation;
 /**  
* @Description: SysRolecontroller类
* @author 
* @date 2020-07-14 07:28:29
* @version V1.0  
 */
@RestController
@RequestMapping("/api/sysRole")
public class SysRoleController extends BaseController {

	/**
	 * iSysRoleService服务注入
	 */
	@Autowired
	private ISysRoleService iSysRoleService;

	/** 
	* @Description: 分页查询表格
	* @param SysRole
	* @param @return  
	* @return BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="SysRole-获取表格数据", notes="获取表格数据")
	@RequestMapping(value = "/getTableList",method = RequestMethod.POST)
	public BaseEntity getTableList(@RequestBody SysRole model)
	{
		
		BaseEntity result = new BaseEntity();
		result = iSysRoleService.tablePagingQuery(model);
		return result;
	}

	/** 
	* @Description: 获取详细信息
	* @param id
	* @param @return  BaseEntity
	* @return  
	* @throws 
	*/ 
	@ApiOperation(value="SysRole-获取详细信息", notes="根据id获取详细信息")
	@RequestMapping(value = "/getDetail",method = RequestMethod.GET)
	@Check({"id:required#主键ID"})
	public BaseEntity getDetail(@RequestParam Long id) throws Exception
	{
		BaseEntity result = iSysRoleService.getDetail(id);
		return result;
	}

	/**
	* @throws Exception  
	* @Description: 新增
	* @param SysRole
	* @return BaseEntity 
	* @throws 
	*/ 
	@ApiOperation(value="SysRole-新增数据", notes="新增数据")
	@RequestMapping(value = "/insert",method = RequestMethod.POST)
	@MethodLog(module="SysRole",remark="新增",operateType=Constants.OPERATE_TYPE_ADD)
	@Check({"roleCode:required#角色代码;length#角色代码#30","roleName:required#角色名;length#角色名#50","roleDesc:required#角色描述;length#角色描述#100",})
	public BaseEntity insert(@RequestBody SysRole model) throws Exception
	{
		BaseEntity result = iSysRoleService.insert(model);
		return result;
	}

	/**
	* @throws Exception  
	* @Description: 更新
	* @param SysRole
	* @return  BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="SysRole-更新数据", notes="更新数据")
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	@MethodLog(module="SysRole",remark="更新",operateType=Constants.OPERATE_TYPE_UPDATE)
	@Check({"id:required#主键ID","roleCode:required#角色代码;length#角色代码#30","roleName:required#角色名;length#角色名#50","roleDesc:required#角色描述;length#角色描述#255",})
	public BaseEntity update(@RequestBody SysRole model) throws Exception
	{
		BaseEntity result = iSysRoleService.update(model);
		return result;
	}

	/** 
	* @Description: 单条删除
	* @param id
	* @return  BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="SysRole-单条删除数据", notes="根据主键删除数据")
	@RequestMapping(value = "/delete",method = RequestMethod.GET)
	@MethodLog(module="SysRole",remark="单条删除",operateType=Constants.OPERATE_TYPE_DELETE)
	@Check({"id:required#主键ID"})
	public BaseEntity delete(@RequestParam Long id)
	{
		BaseEntity result = iSysRoleService.delete(id);
		return result;
	}

	/** 
	* @Description: 批量删除
	* @param SysRole 
	* @return BaseEntity 
	* @throws 
	*/ 
	@ApiOperation(value="SysRole-批量删除", notes="批量删除数据")
	@RequestMapping(value = "/deletebatch",method = RequestMethod.POST)
	@MethodLog(module="SysRole",remark="批量删除",operateType=Constants.OPERATE_TYPE_BATCHDELETE)
	public BaseEntity deletebatch(@RequestBody List<Long> ids)
	{
		BaseEntity result = iSysRoleService.deleteBatch(ids);
		return result;
	}


	 @ApiOperation(value="SysRole-获取角色列表", notes="获取角色列表")
	 @RequestMapping(value = "/getRoles",method = RequestMethod.GET)
	 @MethodLog(module="SysRole",remark="获取角色列表",operateType=Constants.OPERATE_TYPE_SEARCH)
	 public  List<SysRole> getRoles()
	 {
		 List<SysRole> roles = iSysRoleService.getRoles();
		 return roles;
	 }

	 /**
	  * @Title: authed
	  * @Description: 角色菜单授权
	  * @param userRoleDto
	  * @return
	  * @author caiyang
	  * @date 2020-06-11 11:37:42
	  */
	 @ApiOperation(value="UserRole-角色菜单授权", notes="角色菜单授权")
	 @RequestMapping(value = "/authed",method = RequestMethod.POST)
	 @MethodLog(module="UserRole",remark="角色菜单授权",operateType=Constants.OPERATE_TYPE_UPDATE)
	 @Check({"id:required#角色","authed:required#授权内容"})
	 public BaseEntity authed(@RequestBody UserRoleAuthDto userRoleDto, @LoginUser UserInfoDto userInfoDto)
	 {
		 return this.iSysRoleService.authed(userRoleDto);
	 }

}
