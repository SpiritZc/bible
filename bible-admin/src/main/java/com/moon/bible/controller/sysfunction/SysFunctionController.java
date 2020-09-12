/** 
 * 模块：医院入驻平台-SysFunction
 */
package com.moon.bible.controller.sysfunction;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.moon.bible.base.BaseController;
import com.moon.bible.base.BaseEntity;
import com.moon.bible.constants.Constants;
import com.moon.bible.entity.sysfunction.SysFunction;
import com.moon.bible.annotation.Check;
import com.moon.bible.annotation.MethodLog;
import com.moon.bible.api.sysfunction.ISysFunctionService;
import io.swagger.annotations.ApiOperation;
 /**  
* @Description: SysFunctioncontroller类
* @author 
* @date 2020-07-22 08:57:09
* @version V1.0  
 */
@RestController
@RequestMapping("/api/sysFunction")
public class SysFunctionController extends BaseController {

	/**
	 * iSysFunctionService服务注入
	 */
	@Autowired
	private ISysFunctionService iSysFunctionService;

	/**
	* @Description: 分页查询表格
	* @param model
	* @param @return  
	* @return BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="SysFunction-获取表格数据", notes="获取表格数据")
	@RequestMapping(value = "/getTableList",method = RequestMethod.POST)
	public BaseEntity getTableList(@RequestBody SysFunction model)
	{
		
		BaseEntity result = new BaseEntity();
		result = iSysFunctionService.tablePagingQuery(model);
		return result;
	}

	/** 
	* @Description: 获取详细信息
	* @param id
	* @param @return  BaseEntity
	* @return  
	* @throws 
	*/ 
	@ApiOperation(value="SysFunction-获取详细信息", notes="根据id获取详细信息")
	@RequestMapping(value = "/getDetail",method = RequestMethod.GET)
	@Check({"id:required#主键ID"})
	public BaseEntity getDetail(@RequestParam Long id) throws Exception
	{
		BaseEntity result = iSysFunctionService.getDetail(id);
		return result;
	}

	/**
	* @throws Exception  
	* @Description: 新增
	* @param model
	* @return BaseEntity 
	* @throws 
	*/ 
	@ApiOperation(value="SysFunction-新增数据", notes="新增数据")
	@RequestMapping(value = "/insert",method = RequestMethod.POST)
	@MethodLog(module="SysFunction",remark="新增",operateType=Constants.OPERATE_TYPE_ADD)
	@Check({"functionCode:required#功能标识;length#功能标识#50","functionName:required#功能名称;length#功能名称#50","functionUrl:required#功能路径;length#功能路径#100",})
	public BaseEntity insert(@RequestBody SysFunction model) throws Exception
	{
		BaseEntity result = iSysFunctionService.insert(model);
		return result;
	}

	/**
	* @throws Exception  
	* @Description: 更新
	* @param model
	* @return  BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="SysFunction-更新数据", notes="更新数据")
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	@MethodLog(module="SysFunction",remark="更新",operateType=Constants.OPERATE_TYPE_UPDATE)
	@Check({"id:required#主键ID","functionCode:required#功能标识;length#功能标识#50","functionName:required#功能名称;length#功能名称#50","functionUrl:required#功能路径;length#功能路径#100",})
	public BaseEntity update(@RequestBody SysFunction model) throws Exception
	{
		BaseEntity result = iSysFunctionService.update(model);
		return result;
	}

	/** 
	* @Description: 单条删除
	* @param id
	* @return  BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="SysFunction-单条删除数据", notes="根据主键删除数据")
	@RequestMapping(value = "/delete",method = RequestMethod.GET)
	@MethodLog(module="SysFunction",remark="单条删除",operateType=Constants.OPERATE_TYPE_DELETE)
	@Check({"id:required#主键ID"})
	public BaseEntity delete(@RequestParam Long id)
	{
		BaseEntity result = iSysFunctionService.delete(id);
		return result;
	}

	/** 
	* @Description: 批量删除
	* @param SysFunction 
	* @return BaseEntity 
	* @throws 
	*/ 
	@ApiOperation(value="SysFunction-批量删除", notes="批量删除数据")
	@RequestMapping(value = "/deletebatch",method = RequestMethod.POST)
	@MethodLog(module="SysFunction",remark="批量删除",operateType=Constants.OPERATE_TYPE_BATCHDELETE)
	public BaseEntity deletebatch(@RequestBody List<Long> ids)
	{
		BaseEntity result = iSysFunctionService.deleteBatch(ids);
		return result;
	}

	 /**
	  * @Title: updatePermission
	  * @Description: 动态更新权限，不需要重启服务
	  * @return
	  * @author caiyang
	  * @date 2020-06-12 03:53:34
	  */
	 @ApiOperation(value="SysFunction-更新权限", notes="更新权限")
	 @RequestMapping(value = "/updatePermission",method = RequestMethod.POST)
	 public BaseEntity updatePermission() {
		 return this.iSysFunctionService.updatePermission();
	 }
}
