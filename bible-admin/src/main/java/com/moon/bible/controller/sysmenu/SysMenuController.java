/** 
 * 模块：医院入驻平台-SysMenu
 */
package com.moon.bible.controller.sysmenu;

import java.util.List;

import com.moon.bible.annotation.LoginUser;
import com.moon.bible.base.UserInfoDto;
import com.moon.bible.dto.sysmenu.IndexMenuTreeDto;
import com.moon.bible.entity.sysrole.SysRole;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.moon.bible.base.BaseController;
import com.moon.bible.base.BaseEntity;
import com.moon.bible.constants.Constants;
import com.moon.bible.entity.sysmenu.SysMenu;
import com.moon.bible.annotation.Check;
import com.moon.bible.annotation.MethodLog;
import com.moon.bible.api.sysmenu.ISysMenuService;
import io.swagger.annotations.ApiOperation;
 /**  
* @Description: SysMenucontroller类
* @author 
* @date 2020-07-22 08:57:16
* @version V1.0  
 */
@RestController
@RequestMapping("/api/sysMenu")
public class SysMenuController extends BaseController {

	/**
	 * iSysMenuService服务注入
	 */
	@Autowired
	private ISysMenuService iSysMenuService;

	/** 
	* @Description: 分页查询表格
	* @param SysMenu
	* @param @return  
	* @return BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="SysMenu-获取表格数据", notes="获取表格数据")
	@RequestMapping(value = "/getTableList",method = RequestMethod.POST)
	public BaseEntity getTableList(@RequestBody SysMenu model)
	{
		
		BaseEntity result = new BaseEntity();
		result = iSysMenuService.tablePagingQuery(model);
		return result;
	}

	/** 
	* @Description: 获取详细信息
	* @param id
	* @param @return  BaseEntity
	* @return  
	* @throws 
	*/ 
	@ApiOperation(value="SysMenu-获取详细信息", notes="根据id获取详细信息")
	@RequestMapping(value = "/getDetail",method = RequestMethod.GET)
	@Check({"id:required#主键ID"})
	public BaseEntity getDetail(@RequestParam Long id) throws Exception
	{
		BaseEntity result = iSysMenuService.getDetail(id);
		return result;
	}

	/**
	* @throws Exception  
	* @Description: 新增
	* @param SysMenu
	* @return BaseEntity 
	* @throws 
	*/ 
	@ApiOperation(value="SysMenu-新增数据", notes="新增数据")
	@RequestMapping(value = "/insert",method = RequestMethod.POST)
	@MethodLog(module="SysMenu",remark="新增",operateType=Constants.OPERATE_TYPE_ADD)
	@Check({"menuName:required#菜单名称;length#菜单名称#50","menuUrl:required#菜单路径;length#菜单路径#100","menuIcon:required#菜单图标;length#菜单图标#50",})
	public BaseEntity insert(@RequestBody SysMenu model) throws Exception
	{
		BaseEntity result = iSysMenuService.insert(model);
		return result;
	}

	/**
	* @throws Exception  
	* @Description: 更新
	* @param SysMenu
	* @return  BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="SysMenu-更新数据", notes="更新数据")
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	@MethodLog(module="SysMenu",remark="更新",operateType=Constants.OPERATE_TYPE_UPDATE)
	@Check({"id:required#主键ID","menuName:required#菜单名称;length#菜单名称#50","menuUrl:required#菜单路径;length#菜单路径#100","menuIcon:required#菜单图标;length#菜单图标#50",})
	public BaseEntity update(@RequestBody SysMenu model) throws Exception
	{
		BaseEntity result = iSysMenuService.update(model);
		return result;
	}

	/** 
	* @Description: 单条删除
	* @param id
	* @return  BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="SysMenu-单条删除数据", notes="根据主键删除数据")
	@RequestMapping(value = "/delete",method = RequestMethod.GET)
	@MethodLog(module="SysMenu",remark="单条删除",operateType=Constants.OPERATE_TYPE_DELETE)
	@Check({"id:required#主键ID"})
	public BaseEntity delete(@RequestParam Long id)
	{
		BaseEntity result = iSysMenuService.delete(id);
		return result;
	}

	/** 
	* @Description: 批量删除
	* @param SysMenu 
	* @return BaseEntity 
	* @throws 
	*/ 
	@ApiOperation(value="SysMenu-批量删除", notes="批量删除数据")
	@RequestMapping(value = "/deletebatch",method = RequestMethod.POST)
	@MethodLog(module="SysMenu",remark="批量删除",operateType=Constants.OPERATE_TYPE_BATCHDELETE)
	public BaseEntity deletebatch(@RequestBody List<Long> ids)
	{
		BaseEntity result = iSysMenuService.deleteBatch(ids);
		return result;
	}

	 /**
	  * @Title: getIndexMenu
	  * @Description: 获取首页菜单树
	  * @param userInfoDto
	  * @return
	  * @author caiyang
	  * @date 2020-06-11 05:11:36
	  */
	 @ApiOperation(value="SysMenu-获取首页菜单树", notes="获取首页菜单树")
	 @RequestMapping(value = "/getIndexMenu",method = RequestMethod.POST)
	 public List<IndexMenuTreeDto> getIndexMenu(@LoginUser UserInfoDto userInfoDto)
	 {
		 return iSysMenuService.getIndexMenu(userInfoDto);
	 }


	 /**
	  * @Title: getAuthTree
	  * @Description: 获取角色授权树
	  * @param userRole
	  * @param userInfoDto
	  * @return
	  * @author caiyang
	  * @date 2020-06-11 09:57:11
	  */
	 @ApiOperation(value="SysMenu-获取角色授权树", notes="获取角色授权树")
	 @RequestMapping(value = "/getAuthTree",method = RequestMethod.POST)
	 public BaseEntity getAuthTree(@RequestBody SysRole sysRole, @LoginUser UserInfoDto userInfoDto)
	 {
		 return iSysMenuService.getAuthTree(sysRole, userInfoDto);
	 }

	 /**
	  * @Title: getAllMenus
	  * @Description: 获取所有的后台菜单
	  * @return
	  * @author caiyang
	  * @date 2020-06-16 01:18:48
	  */
	 @ApiOperation(value="SysMenu-获取所有的后台菜单", notes="获取所有的后台菜单")
	 @RequestMapping(value = "/getAllMenus",method = RequestMethod.POST)
	 public List<SysMenu> getAllMenus(){
		 return this.iSysMenuService.getAllMenus();
	 }
}
