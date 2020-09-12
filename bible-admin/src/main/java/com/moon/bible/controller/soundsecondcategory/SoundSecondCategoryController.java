/** 
 * 模块：bible-SoundSecondCategory
 */
package com.moon.bible.controller.soundsecondcategory;

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
import com.moon.bible.entity.soundsecondcategory.SoundSecondCategory;
import com.moon.bible.annotation.Check;
import com.moon.bible.annotation.MethodLog;
import com.moon.bible.api.soundsecondcategory.ISoundSecondCategoryService;
import io.swagger.annotations.ApiOperation;
 /**  
* @Description: SoundSecondCategorycontroller类
* @author 
* @date 2020-09-06 03:43:04
* @version V1.0  
 */
@RestController
@RequestMapping("/api/soundSecondCategory")
public class SoundSecondCategoryController extends BaseController {

	/**
	 * iSoundSecondCategoryService服务注入
	 */
	@Autowired
	private ISoundSecondCategoryService iSoundSecondCategoryService;

	/** 
	* @Description: 分页查询表格
	* @param SoundSecondCategory
	* @param @return  
	* @return BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="SoundSecondCategory-获取表格数据", notes="获取表格数据")
	@RequestMapping(value = "/getTableList",method = RequestMethod.POST)
	public BaseEntity getTableList(@RequestBody SoundSecondCategory model)
	{
		
		BaseEntity result = new BaseEntity();
		result = iSoundSecondCategoryService.tablePagingQuery(model);
		return result;
	}

	/** 
	* @Description: 获取详细信息
	* @param id
	* @param @return  BaseEntity
	* @return  
	* @throws 
	*/ 
	@ApiOperation(value="SoundSecondCategory-获取详细信息", notes="根据id获取详细信息")
	@RequestMapping(value = "/getDetail",method = RequestMethod.GET)
	@Check({"id:required#主键ID"})
	public BaseEntity getDetail(@RequestParam Long id) throws Exception
	{
		BaseEntity result = iSoundSecondCategoryService.getDetail(id);
		return result;
	}

	/**
	* @throws Exception  
	* @Description: 新增
	* @param SoundSecondCategory
	* @return BaseEntity 
	* @throws 
	*/ 
	@ApiOperation(value="SoundSecondCategory-新增数据", notes="新增数据")
	@RequestMapping(value = "/insert",method = RequestMethod.POST)
	@MethodLog(module="SoundSecondCategory",remark="新增",operateType=Constants.OPERATE_TYPE_ADD)
	@Check({"categorySecondName:required#二级类别名;length#二级类别名#50",})
	public BaseEntity insert(@RequestBody SoundSecondCategory model) throws Exception
	{
		BaseEntity result = iSoundSecondCategoryService.insert(model);
		return result;
	}

	/**
	* @throws Exception  
	* @Description: 更新
	* @param SoundSecondCategory
	* @return  BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="SoundSecondCategory-更新数据", notes="更新数据")
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	@MethodLog(module="SoundSecondCategory",remark="更新",operateType=Constants.OPERATE_TYPE_UPDATE)
	@Check({"id:required#主键ID","categorySecondName:required#二级类别名;length#二级类别名#50",})
	public BaseEntity update(@RequestBody SoundSecondCategory model) throws Exception
	{
		BaseEntity result = iSoundSecondCategoryService.update(model);
		return result;
	}

	/** 
	* @Description: 单条删除
	* @param id
	* @return  BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="SoundSecondCategory-单条删除数据", notes="根据主键删除数据")
	@RequestMapping(value = "/delete",method = RequestMethod.GET)
	@MethodLog(module="SoundSecondCategory",remark="单条删除",operateType=Constants.OPERATE_TYPE_DELETE)
	@Check({"id:required#主键ID"})
	public BaseEntity delete(@RequestParam Long id)
	{
		BaseEntity result = iSoundSecondCategoryService.delete(id);
		return result;
	}

	/** 
	* @Description: 批量删除
	* @param SoundSecondCategory 
	* @return BaseEntity 
	* @throws 
	*/ 
	@ApiOperation(value="SoundSecondCategory-批量删除", notes="批量删除数据")
	@RequestMapping(value = "/deletebatch",method = RequestMethod.POST)
	@MethodLog(module="SoundSecondCategory",remark="批量删除",operateType=Constants.OPERATE_TYPE_BATCHDELETE)
	public BaseEntity deletebatch(@RequestBody List<Long> ids)
	{
		BaseEntity result = iSoundSecondCategoryService.deleteBatch(ids);
		return result;
	}
}
