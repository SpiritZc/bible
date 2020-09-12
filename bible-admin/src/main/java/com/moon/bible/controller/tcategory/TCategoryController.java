/** 
 * 模块：医院入驻平台-TCategory
 */
package com.moon.bible.controller.tcategory;

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
import com.moon.bible.entity.tcategory.TCategory;
import com.moon.bible.annotation.Check;
import com.moon.bible.annotation.MethodLog;
import com.moon.bible.api.tcategory.ITCategoryService;
import io.swagger.annotations.ApiOperation;
 /**  
* @Description: TCategorycontroller类
* @author 
* @date 2020-07-30 10:22:12
* @version V1.0  
 */
@RestController
@RequestMapping("/api/tCategory")
public class TCategoryController extends BaseController {

	/**
	 * iTCategoryService服务注入
	 */
	@Autowired
	private ITCategoryService iTCategoryService;

	/** 
	* @Description: 分页查询表格
	* @param TCategory
	* @param @return  
	* @return BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="TCategory-获取表格数据", notes="获取表格数据")
	@RequestMapping(value = "/getTableList",method = RequestMethod.POST)
	public BaseEntity getTableList(@RequestBody TCategory model)
	{
		
		BaseEntity result = new BaseEntity();
		result = iTCategoryService.tablePagingQuery(model);
		return result;
	}

	/** 
	* @Description: 获取详细信息
	* @param id
	* @param @return  BaseEntity
	* @return  
	* @throws 
	*/ 
	@ApiOperation(value="TCategory-获取详细信息", notes="根据id获取详细信息")
	@RequestMapping(value = "/getDetail",method = RequestMethod.GET)
	@Check({"id:required#主键ID"})
	public BaseEntity getDetail(@RequestParam Long id) throws Exception
	{
		BaseEntity result = iTCategoryService.getDetail(id);
		return result;
	}

	/**
	* @throws Exception  
	* @Description: 新增
	* @param TCategory
	* @return BaseEntity 
	* @throws 
	*/ 
	@ApiOperation(value="TCategory-新增数据", notes="新增数据")
	@RequestMapping(value = "/insert",method = RequestMethod.POST)
	@MethodLog(module="TCategory",remark="新增",operateType=Constants.OPERATE_TYPE_ADD)
	@Check({"name:required#类别名称;length#类别名称#50",})
	public BaseEntity insert(@RequestBody TCategory model) throws Exception
	{
		BaseEntity result = iTCategoryService.insert(model);
		return result;
	}

	/**
	* @throws Exception  
	* @Description: 更新
	* @param TCategory
	* @return  BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="TCategory-更新数据", notes="更新数据")
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	@MethodLog(module="TCategory",remark="更新",operateType=Constants.OPERATE_TYPE_UPDATE)
	@Check({"id:required#主键ID","name:required#类别名称;length#类别名称#50",})
	public BaseEntity update(@RequestBody TCategory model) throws Exception
	{
		BaseEntity result = iTCategoryService.update(model);
		return result;
	}

	/** 
	* @Description: 单条删除
	* @param id
	* @return  BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="TCategory-单条删除数据", notes="根据主键删除数据")
	@RequestMapping(value = "/delete",method = RequestMethod.GET)
	@MethodLog(module="TCategory",remark="单条删除",operateType=Constants.OPERATE_TYPE_DELETE)
	@Check({"id:required#主键ID"})
	public BaseEntity delete(@RequestParam Long id)
	{
		BaseEntity result = iTCategoryService.delete(id);
		return result;
	}

	/** 
	* @Description: 批量删除
	* @param TCategory 
	* @return BaseEntity 
	* @throws 
	*/ 
	@ApiOperation(value="TCategory-批量删除", notes="批量删除数据")
	@RequestMapping(value = "/deletebatch",method = RequestMethod.POST)
	@MethodLog(module="TCategory",remark="批量删除",operateType=Constants.OPERATE_TYPE_BATCHDELETE)
	public BaseEntity deletebatch(@RequestBody List<Long> ids)
	{
		BaseEntity result = iTCategoryService.deleteBatch(ids);
		return result;
	}

	 /**
	  * @Description: 查询类别列表
	  * @return List<TCategory>
	  * @throws
	  */
	 @ApiOperation(value="TCategory-查询类别列表", notes="查询类别列表")
	 @RequestMapping(value = "/getAllCategory",method = RequestMethod.GET)
	 @MethodLog(module="TCategory",remark="查询类别列表",operateType=Constants.OPERATE_TYPE_SEARCH)
	 public List<TCategory> getAllCategory()
	 {
		 List<TCategory> tCategories = iTCategoryService.getAllCategory();
		 return tCategories;
	 }

}
