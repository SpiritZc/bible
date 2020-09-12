/** 
 * 模块：医院入驻平台-FileLog
 */
package com.moon.bible.controller.filelog;

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
import com.moon.bible.entity.filelog.FileLog;
import com.moon.bible.annotation.Check;
import com.moon.bible.annotation.MethodLog;
import com.moon.bible.api.filelog.IFileLogService;
import io.swagger.annotations.ApiOperation;
 /**  
* @Description: FileLogcontroller类
* @author 
* @date 2020-07-29 11:18:41
* @version V1.0  
 */
@RestController
@RequestMapping("/api/fileLog")
public class FileLogController extends BaseController {

	/**
	 * iFileLogService服务注入
	 */
	@Autowired
	private IFileLogService iFileLogService;

	/** 
	* @Description: 分页查询表格
	* @param FileLog
	* @param @return  
	* @return BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="FileLog-获取表格数据", notes="获取表格数据")
	@RequestMapping(value = "/getTableList",method = RequestMethod.POST)
	public BaseEntity getTableList(@RequestBody FileLog model)
	{
		
		BaseEntity result = new BaseEntity();
		result = iFileLogService.tablePagingQuery(model);
		return result;
	}

	/** 
	* @Description: 获取详细信息
	* @param id
	* @param @return  BaseEntity
	* @return  
	* @throws 
	*/ 
	@ApiOperation(value="FileLog-获取详细信息", notes="根据id获取详细信息")
	@RequestMapping(value = "/getDetail",method = RequestMethod.GET)
	@Check({"id:required#主键ID"})
	public BaseEntity getDetail(@RequestParam Long id) throws Exception
	{
		BaseEntity result = iFileLogService.getDetail(id);
		return result;
	}

	/**
	* @throws Exception  
	* @Description: 新增
	* @param FileLog
	* @return BaseEntity 
	* @throws 
	*/ 
	@ApiOperation(value="FileLog-新增数据", notes="新增数据")
	@RequestMapping(value = "/insert",method = RequestMethod.POST)
	@MethodLog(module="FileLog",remark="新增",operateType=Constants.OPERATE_TYPE_ADD)
	@Check({"fileName:required#文件名;length#文件名#200","errorInfo:required#异常信息;length#异常信息#500","result:required#返回结果;length#返回结果#2000","fileUrl:required#访问路径;length#访问路径#500","operateIp:required#请求机器ip;length#请求机器ip#20","executeTime:required#执行时长;length#执行时长#50",})
	public BaseEntity insert(@RequestBody FileLog model) throws Exception
	{
		BaseEntity result = iFileLogService.insert(model);
		return result;
	}

	/**
	* @throws Exception  
	* @Description: 更新
	* @param FileLog
	* @return  BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="FileLog-更新数据", notes="更新数据")
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	@MethodLog(module="FileLog",remark="更新",operateType=Constants.OPERATE_TYPE_UPDATE)
	@Check({"id:required#主键ID","fileName:required#文件名;length#文件名#200","errorInfo:required#异常信息;length#异常信息#500","result:required#返回结果;length#返回结果#2000","fileUrl:required#访问路径;length#访问路径#500","operateIp:required#请求机器ip;length#请求机器ip#20","executeTime:required#执行时长;length#执行时长#50",})
	public BaseEntity update(@RequestBody FileLog model) throws Exception
	{
		BaseEntity result = iFileLogService.update(model);
		return result;
	}

	/** 
	* @Description: 单条删除
	* @param id
	* @return  BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="FileLog-单条删除数据", notes="根据主键删除数据")
	@RequestMapping(value = "/delete",method = RequestMethod.GET)
	@MethodLog(module="FileLog",remark="单条删除",operateType=Constants.OPERATE_TYPE_DELETE)
	@Check({"id:required#主键ID"})
	public BaseEntity delete(@RequestParam Long id)
	{
		BaseEntity result = iFileLogService.delete(id);
		return result;
	}

	/** 
	* @Description: 批量删除
	* @param FileLog 
	* @return BaseEntity 
	* @throws 
	*/ 
	@ApiOperation(value="FileLog-批量删除", notes="批量删除数据")
	@RequestMapping(value = "/deletebatch",method = RequestMethod.POST)
	@MethodLog(module="FileLog",remark="批量删除",operateType=Constants.OPERATE_TYPE_BATCHDELETE)
	public BaseEntity deletebatch(@RequestBody List<Long> ids)
	{
		BaseEntity result = iFileLogService.deleteBatch(ids);
		return result;
	}
}
