/** 
 * 模块：医院入驻平台-OperateLog
 */
package com.moon.bible.controller.operatelog;

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
import com.moon.bible.entity.operatelog.OperateLog;
import com.moon.bible.annotation.Check;
import com.moon.bible.annotation.MethodLog;
import com.moon.bible.api.operatelog.IOperateLogService;
import io.swagger.annotations.ApiOperation;
 /**  
* @Description: OperateLogcontroller类
* @author 
* @date 2020-07-27 11:27:43
* @version V1.0  
 */
@RestController
@RequestMapping("/api/operateLog")
public class OperateLogController extends BaseController {

	/**
	 * iOperateLogService服务注入
	 */
	@Autowired
	private IOperateLogService iOperateLogService;

	/** 
	* @Description: 分页查询表格
	* @param OperateLog
	* @param @return  
	* @return BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="OperateLog-获取表格数据", notes="获取表格数据")
	@RequestMapping(value = "/getTableList",method = RequestMethod.POST)
	public BaseEntity getTableList(@RequestBody OperateLog model)
	{
		
		BaseEntity result = new BaseEntity();
		result = iOperateLogService.tablePagingQuery(model);
		return result;
	}

	/** 
	* @Description: 获取详细信息
	* @param id
	* @param @return  BaseEntity
	* @return  
	* @throws 
	*/ 
	@ApiOperation(value="OperateLog-获取详细信息", notes="根据id获取详细信息")
	@RequestMapping(value = "/getDetail",method = RequestMethod.GET)
	@Check({"id:required#主键ID"})
	public BaseEntity getDetail(@RequestParam Long id) throws Exception
	{
		BaseEntity result = iOperateLogService.getDetail(id);
		return result;
	}

	/**
	* @throws Exception  
	* @Description: 新增
	* @param OperateLog
	* @return BaseEntity 
	* @throws 
	*/ 
	@ApiOperation(value="OperateLog-新增数据", notes="新增数据")
	@RequestMapping(value = "/insert",method = RequestMethod.POST)
	@MethodLog(module="OperateLog",remark="新增",operateType=Constants.OPERATE_TYPE_ADD)
	@Check({"operateType:required#操作类型;length#操作类型#20","operateRemark:required#操作注释;length#操作注释#50","operateMethod:required#请求操作的方法;length#请求操作的方法#255","operateParams:required#请求参数;length#请求参数#2147483647","errorInfo:required#错误消息;length#错误消息#2147483647","result:required#返回结果;length#返回结果#2147483647","operateIp:required#请求机器ip;length#请求机器ip#20","executeTime:required#执行时长;length#执行时长#20",})
	public BaseEntity insert(@RequestBody OperateLog model) throws Exception
	{
		BaseEntity result = iOperateLogService.insert(model);
		return result;
	}

	/**
	* @throws Exception  
	* @Description: 更新
	* @param OperateLog
	* @return  BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="OperateLog-更新数据", notes="更新数据")
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	@MethodLog(module="OperateLog",remark="更新",operateType=Constants.OPERATE_TYPE_UPDATE)
	@Check({"id:required#主键ID","operateType:required#操作类型;length#操作类型#20","operateRemark:required#操作注释;length#操作注释#50","operateMethod:required#请求操作的方法;length#请求操作的方法#255","operateParams:required#请求参数;length#请求参数#2147483647","errorInfo:required#错误消息;length#错误消息#2147483647","result:required#返回结果;length#返回结果#2147483647","operateIp:required#请求机器ip;length#请求机器ip#20","executeTime:required#执行时长;length#执行时长#20",})
	public BaseEntity update(@RequestBody OperateLog model) throws Exception
	{
		BaseEntity result = iOperateLogService.update(model);
		return result;
	}

	/** 
	* @Description: 单条删除
	* @param id
	* @return  BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="OperateLog-单条删除数据", notes="根据主键删除数据")
	@RequestMapping(value = "/delete",method = RequestMethod.GET)
	@MethodLog(module="OperateLog",remark="单条删除",operateType=Constants.OPERATE_TYPE_DELETE)
	@Check({"id:required#主键ID"})
	public BaseEntity delete(@RequestParam Long id)
	{
		BaseEntity result = iOperateLogService.delete(id);
		return result;
	}

	/** 
	* @Description: 批量删除
	* @param OperateLog 
	* @return BaseEntity 
	* @throws 
	*/ 
	@ApiOperation(value="OperateLog-批量删除", notes="批量删除数据")
	@RequestMapping(value = "/deletebatch",method = RequestMethod.POST)
	@MethodLog(module="OperateLog",remark="批量删除",operateType=Constants.OPERATE_TYPE_BATCHDELETE)
	public BaseEntity deletebatch(@RequestBody List<Long> ids)
	{
		BaseEntity result = iOperateLogService.deleteBatch(ids);
		return result;
	}
}
