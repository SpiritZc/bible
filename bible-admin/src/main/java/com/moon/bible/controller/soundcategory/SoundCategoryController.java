/** 
 * 模块：bible-SoundCategory
 */
package com.moon.bible.controller.soundcategory;

import java.util.List;

import com.moon.bible.entity.soundcategory.SoundCategory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.moon.bible.base.BaseController;
import com.moon.bible.base.BaseEntity;
import com.moon.bible.constants.Constants;
import com.moon.bible.annotation.Check;
import com.moon.bible.annotation.MethodLog;
import com.moon.bible.api.soundcategory.ISoundCategoryService;
import io.swagger.annotations.ApiOperation;
 /**  
* @Description: SoundCategorycontroller类
* @author 
* @date 2020-08-26 08:51:23
* @version V1.0  
 */
@RestController
@RequestMapping("/api/soundCategory")
public class SoundCategoryController extends BaseController {

	/**
	 * iSoundCategoryService服务注入
	 */
	@Autowired
	private ISoundCategoryService iSoundCategoryService;

	/** 
	* @Description: 分页查询表格
	* @param SoundCategory
	* @param @return  
	* @return BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="SoundCategory-获取表格数据", notes="获取表格数据")
	@RequestMapping(value = "/getTableList",method = RequestMethod.POST)
	public BaseEntity getTableList(@RequestBody SoundCategory model)
	{
		
		BaseEntity result = new BaseEntity();
		result = iSoundCategoryService.tablePagingQuery(model);
		return result;
	}

	/** 
	* @Description: 获取详细信息
	* @param id
	* @param @return  BaseEntity
	* @return  
	* @throws 
	*/ 
	@ApiOperation(value="SoundCategory-获取详细信息", notes="根据id获取详细信息")
	@RequestMapping(value = "/getDetail",method = RequestMethod.GET)
	@Check({"id:required#主键ID"})
	public BaseEntity getDetail(@RequestParam Long id) throws Exception
	{
		BaseEntity result = iSoundCategoryService.getDetail(id);
		return result;
	}

	/**
	* @throws Exception  
	* @Description: 新增
	* @param SoundCategory
	* @return BaseEntity 
	* @throws 
	*/ 
	@ApiOperation(value="SoundCategory-新增数据", notes="新增数据")
	@RequestMapping(value = "/insert",method = RequestMethod.POST)
	@MethodLog(module="SoundCategory",remark="新增",operateType=Constants.OPERATE_TYPE_ADD)
	@Check({})
	public BaseEntity insert(@RequestBody SoundCategory model) throws Exception
	{
		BaseEntity result = iSoundCategoryService.insert(model);
		return result;
	}

	/**
	* @throws Exception  
	* @Description: 更新
	* @param SoundCategory
	* @return  BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="SoundCategory-更新数据", notes="更新数据")
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	@MethodLog(module="SoundCategory",remark="更新",operateType=Constants.OPERATE_TYPE_UPDATE)
	@Check({"id:required#主键ID",})
	public BaseEntity update(@RequestBody SoundCategory model) throws Exception
	{
		BaseEntity result = iSoundCategoryService.update(model);
		return result;
	}

	/** 
	* @Description: 单条删除
	* @param id
	* @return  BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="SoundCategory-单条删除数据", notes="根据主键删除数据")
	@RequestMapping(value = "/delete",method = RequestMethod.GET)
	@MethodLog(module="SoundCategory",remark="单条删除",operateType=Constants.OPERATE_TYPE_DELETE)
	@Check({"id:required#主键ID"})
	public BaseEntity delete(@RequestParam Long id)
	{
		BaseEntity result = iSoundCategoryService.delete(id);
		return result;
	}

	/** 
	* @Description: 批量删除
	* @param SoundCategory 
	* @return BaseEntity 
	* @throws 
	*/ 
	@ApiOperation(value="SoundCategory-批量删除", notes="批量删除数据")
	@RequestMapping(value = "/deletebatch",method = RequestMethod.POST)
	@MethodLog(module="SoundCategory",remark="批量删除",operateType=Constants.OPERATE_TYPE_BATCHDELETE)
	public BaseEntity deletebatch(@RequestBody List<Long> ids)
	{
		BaseEntity result = iSoundCategoryService.deleteBatch(ids);
		return result;
	}
}
