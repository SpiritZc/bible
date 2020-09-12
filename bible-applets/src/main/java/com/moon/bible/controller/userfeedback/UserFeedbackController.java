/** 
 * 模块：bible-UserFeedback
 */
package com.moon.bible.controller.userfeedback;

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
import com.moon.bible.entity.userfeedback.UserFeedback;
import com.moon.bible.annotation.Check;
import com.moon.bible.annotation.MethodLog;
import com.moon.bible.api.userfeedback.IUserFeedbackService;
import io.swagger.annotations.ApiOperation;
 /**  
* @Description: UserFeedbackcontroller类
* @author 
* @date 2020-09-12 12:17:50
* @version V1.0  
 */
@RestController
@RequestMapping("/api/userFeedback")
public class UserFeedbackController extends BaseController {

	/**
	 * iUserFeedbackService服务注入
	 */
	@Autowired
	private IUserFeedbackService iUserFeedbackService;

	/** 
	* @Description: 分页查询表格
	* @param UserFeedback
	* @param @return  
	* @return BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="UserFeedback-获取表格数据", notes="获取表格数据")
	@RequestMapping(value = "/getTableList",method = RequestMethod.POST)
	public BaseEntity getTableList(@RequestBody UserFeedback model)
	{
		
		BaseEntity result = new BaseEntity();
		result = iUserFeedbackService.tablePagingQuery(model);
		return result;
	}

	/** 
	* @Description: 获取详细信息
	* @param id
	* @param @return  BaseEntity
	* @return  
	* @throws 
	*/ 
	@ApiOperation(value="UserFeedback-获取详细信息", notes="根据id获取详细信息")
	@RequestMapping(value = "/getDetail",method = RequestMethod.GET)
	@Check({"id:required#主键ID"})
	public BaseEntity getDetail(@RequestParam Long id) throws Exception
	{
		BaseEntity result = iUserFeedbackService.getDetail(id);
		return result;
	}

	/**
	* @throws Exception  
	* @Description: 新增
	* @param UserFeedback
	* @return BaseEntity 
	* @throws 
	*/ 
	@ApiOperation(value="UserFeedback-新增数据", notes="新增数据")
	@RequestMapping(value = "/insert",method = RequestMethod.POST)
	@MethodLog(module="UserFeedback",remark="新增",operateType=Constants.OPERATE_TYPE_ADD)
	@Check({})
	public BaseEntity insert(@RequestBody UserFeedback model) throws Exception
	{
		BaseEntity result = iUserFeedbackService.insert(model);
		return result;
	}

	/**
	* @throws Exception  
	* @Description: 更新
	* @param UserFeedback
	* @return  BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="UserFeedback-更新数据", notes="更新数据")
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	@MethodLog(module="UserFeedback",remark="更新",operateType=Constants.OPERATE_TYPE_UPDATE)
	@Check({"id:required#主键ID",})
	public BaseEntity update(@RequestBody UserFeedback model) throws Exception
	{
		BaseEntity result = iUserFeedbackService.update(model);
		return result;
	}

	/** 
	* @Description: 单条删除
	* @param id
	* @return  BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="UserFeedback-单条删除数据", notes="根据主键删除数据")
	@RequestMapping(value = "/delete",method = RequestMethod.GET)
	@MethodLog(module="UserFeedback",remark="单条删除",operateType=Constants.OPERATE_TYPE_DELETE)
	@Check({"id:required#主键ID"})
	public BaseEntity delete(@RequestParam Long id)
	{
		BaseEntity result = iUserFeedbackService.delete(id);
		return result;
	}

	/** 
	* @Description: 批量删除
	* @param UserFeedback 
	* @return BaseEntity 
	* @throws 
	*/ 
	@ApiOperation(value="UserFeedback-批量删除", notes="批量删除数据")
	@RequestMapping(value = "/deletebatch",method = RequestMethod.POST)
	@MethodLog(module="UserFeedback",remark="批量删除",operateType=Constants.OPERATE_TYPE_BATCHDELETE)
	public BaseEntity deletebatch(@RequestBody List<Long> ids)
	{
		BaseEntity result = iUserFeedbackService.deleteBatch(ids);
		return result;
	}
}
