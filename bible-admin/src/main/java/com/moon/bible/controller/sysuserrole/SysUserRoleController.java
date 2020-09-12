/** 
 * 模块：bible-SysUserRole
 */
package com.moon.bible.controller.sysuserrole;

import java.util.List;

import com.moon.bible.entity.sysuser.SysUser;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.moon.bible.base.BaseController;
import com.moon.bible.base.BaseEntity;
import com.moon.bible.constants.Constants;
import com.moon.bible.entity.sysuserrole.SysUserRole;
import com.moon.bible.annotation.Check;
import com.moon.bible.annotation.MethodLog;
import com.moon.bible.api.sysuserrole.ISysUserRoleService;
import io.swagger.annotations.ApiOperation;
 /**  
* @Description: SysUserRolecontroller类
* @author 
* @date 2020-07-14 07:28:43
* @version V1.0  
 */
@RestController
@RequestMapping("/api/sysUserRole")
public class SysUserRoleController extends BaseController {

	/**
	 * iSysUserRoleService服务注入
	 */
	@Autowired
	private ISysUserRoleService iSysUserRoleService;

	/** 
	* @Description: 分页查询表格
	* @param SysUserRole
	* @param @return  
	* @return BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="SysUserRole-获取表格数据", notes="获取表格数据")
	@RequestMapping(value = "/getTableList",method = RequestMethod.POST)
	public BaseEntity getTableList(@RequestBody SysUserRole model)
	{
		
		BaseEntity result = new BaseEntity();
		result = iSysUserRoleService.tablePagingQuery(model);
		return result;
	}

	/** 
	* @Description: 获取详细信息
	* @param id
	* @param @return  BaseEntity
	* @return  
	* @throws 
	*/ 
	@ApiOperation(value="SysUserRole-获取详细信息", notes="根据id获取详细信息")
	@RequestMapping(value = "/getDetail",method = RequestMethod.GET)
	@Check({"id:required#主键ID"})
	public BaseEntity getDetail(@RequestParam Long id) throws Exception
	{
		BaseEntity result = iSysUserRoleService.getDetail(id);
		return result;
	}

	/**
	* @throws Exception  
	* @Description: 新增
	* @param SysUserRole
	* @return BaseEntity 
	* @throws 
	*/ 
	@ApiOperation(value="SysUserRole-新增数据", notes="新增数据")
	@RequestMapping(value = "/insert",method = RequestMethod.POST)
	@MethodLog(module="SysUserRole",remark="新增",operateType=Constants.OPERATE_TYPE_ADD)
	@Check({})
	public BaseEntity insert(@RequestBody SysUserRole model) throws Exception
	{
		BaseEntity result = iSysUserRoleService.insert(model);
		return result;
	}

	/**
	* @throws Exception  
	* @Description: 更新
	* @param SysUserRole
	* @return  BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="SysUserRole-更新数据", notes="更新数据")
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	@MethodLog(module="SysUserRole",remark="更新",operateType=Constants.OPERATE_TYPE_UPDATE)
	@Check({"id:required#主键ID",})
	public BaseEntity update(@RequestBody SysUserRole model) throws Exception
	{
		BaseEntity result = iSysUserRoleService.update(model);
		return result;
	}

	/** 
	* @Description: 单条删除
	* @param id
	* @return  BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="SysUserRole-单条删除数据", notes="根据主键删除数据")
	@RequestMapping(value = "/delete",method = RequestMethod.GET)
	@MethodLog(module="SysUserRole",remark="单条删除",operateType=Constants.OPERATE_TYPE_DELETE)
	@Check({"id:required#主键ID"})
	public BaseEntity delete(@RequestParam Long id)
	{
		BaseEntity result = iSysUserRoleService.delete(id);
		return result;
	}

	/** 
	* @Description: 批量删除
	* @param SysUserRole 
	* @return BaseEntity 
	* @throws 
	*/ 
	@ApiOperation(value="SysUserRole-批量删除", notes="批量删除数据")
	@RequestMapping(value = "/deletebatch",method = RequestMethod.POST)
	@MethodLog(module="SysUserRole",remark="批量删除",operateType=Constants.OPERATE_TYPE_BATCHDELETE)
	public BaseEntity deletebatch(@RequestBody List<Long> ids)
	{
		BaseEntity result = iSysUserRoleService.deleteBatch(ids);
		return result;
	}


}
