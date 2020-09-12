/** 
 * 模块：bible-UserLoginLog
 */
package com.moon.bible.controller.userloginlog;

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
import com.moon.bible.entity.userloginlog.UserLoginLog;
import com.moon.bible.annotation.Check;
import com.moon.bible.annotation.MethodLog;
import com.moon.bible.api.userloginlog.IUserLoginLogService;
import io.swagger.annotations.ApiOperation;
 /**  
* @Description: UserLoginLogcontroller类
* @author 
* @date 2020-07-18 03:21:00
* @version V1.0  
 */
@RestController
@RequestMapping("/api/userLoginLog")
public class UserLoginLogController extends BaseController {

	/**
	 * iUserLoginLogService服务注入
	 */
	@Autowired
	private IUserLoginLogService iUserLoginLogService;

	/** 
	* @Description: 分页查询表格
	* @param UserLoginLog
	* @param @return  
	* @return BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="UserLoginLog-获取表格数据", notes="获取表格数据")
	@RequestMapping(value = "/getTableList",method = RequestMethod.POST)
	public BaseEntity getTableList(@RequestBody UserLoginLog model)
	{
		
		BaseEntity result = new BaseEntity();
		result = iUserLoginLogService.tablePagingQuery(model);
		return result;
	}

	/** 
	* @Description: 获取详细信息
	* @param id
	* @param @return  BaseEntity
	* @return  
	* @throws 
	*/ 
	@ApiOperation(value="UserLoginLog-获取详细信息", notes="根据id获取详细信息")
	@RequestMapping(value = "/getDetail",method = RequestMethod.GET)
	@Check({"id:required#主键ID"})
	public BaseEntity getDetail(@RequestParam Long id) throws Exception
	{
		BaseEntity result = iUserLoginLogService.getDetail(id);
		return result;
	}

	/**
	* @throws Exception  
	* @Description: 新增
	* @param UserLoginLog
	* @return BaseEntity 
	* @throws 
	*/ 
	@ApiOperation(value="UserLoginLog-新增数据", notes="新增数据")
	@RequestMapping(value = "/insert",method = RequestMethod.POST)
	@MethodLog(module="UserLoginLog",remark="新增",operateType=Constants.OPERATE_TYPE_ADD)
	@Check({"accountName:required#登录账号;length#登录账号#100","operateMethod:required#请求操作的方法;length#请求操作的方法#50","operateParams:required#请求参数;length#请求参数#255","errorInfo:required#错误消息;length#错误消息#100","result:required#返回结果;length#返回结果#100","operateIp:required#请求机器ip;length#请求机器ip#50",})
	public BaseEntity insert(@RequestBody UserLoginLog model) throws Exception
	{
		BaseEntity result = iUserLoginLogService.insert(model);
		return result;
	}

	/**
	* @throws Exception  
	* @Description: 更新
	* @param UserLoginLog
	* @return  BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="UserLoginLog-更新数据", notes="更新数据")
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	@MethodLog(module="UserLoginLog",remark="更新",operateType=Constants.OPERATE_TYPE_UPDATE)
	@Check({"id:required#主键ID","accountName:required#登录账号;length#登录账号#100","operateMethod:required#请求操作的方法;length#请求操作的方法#50","operateParams:required#请求参数;length#请求参数#255","errorInfo:required#错误消息;length#错误消息#100","result:required#返回结果;length#返回结果#100","operateIp:required#请求机器ip;length#请求机器ip#50",})
	public BaseEntity update(@RequestBody UserLoginLog model) throws Exception
	{
		BaseEntity result = iUserLoginLogService.update(model);
		return result;
	}

	/** 
	* @Description: 单条删除
	* @param id
	* @return  BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="UserLoginLog-单条删除数据", notes="根据主键删除数据")
	@RequestMapping(value = "/delete",method = RequestMethod.GET)
	@MethodLog(module="UserLoginLog",remark="单条删除",operateType=Constants.OPERATE_TYPE_DELETE)
	@Check({"id:required#主键ID"})
	public BaseEntity delete(@RequestParam Long id)
	{
		BaseEntity result = iUserLoginLogService.delete(id);
		return result;
	}

	/** 
	* @Description: 批量删除
	* @param UserLoginLog 
	* @return BaseEntity 
	* @throws 
	*/ 
	@ApiOperation(value="UserLoginLog-批量删除", notes="批量删除数据")
	@RequestMapping(value = "/deletebatch",method = RequestMethod.POST)
	@MethodLog(module="UserLoginLog",remark="批量删除",operateType=Constants.OPERATE_TYPE_BATCHDELETE)
	public BaseEntity deletebatch(@RequestBody List<Long> ids)
	{
		BaseEntity result = iUserLoginLogService.deleteBatch(ids);
		return result;
	}
}
