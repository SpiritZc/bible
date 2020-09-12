/** 
 * 模块：bible-TChapter
 */
package com.moon.bible.controller.tchapter;

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
import com.moon.bible.entity.tchapter.TChapter;
import com.moon.bible.annotation.Check;
import com.moon.bible.annotation.MethodLog;
import com.moon.bible.api.tchapter.ITChapterService;
import io.swagger.annotations.ApiOperation;
 /**  
* @Description: TChaptercontroller类
* @author 
* @date 2020-08-22 08:31:36
* @version V1.0  
 */
@RestController
@RequestMapping("/api/tChapter")
public class TChapterController extends BaseController {

	/**
	 * iTChapterService服务注入
	 */
	@Autowired
	private ITChapterService iTChapterService;

	/** 
	* @Description: 分页查询表格
	* @param TChapter
	* @param @return  
	* @return BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="TChapter-获取表格数据", notes="获取表格数据")
	@RequestMapping(value = "/getTableList",method = RequestMethod.POST)
	public BaseEntity getTableList(@RequestBody TChapter model)
	{
		
		BaseEntity result = new BaseEntity();
		result = iTChapterService.tablePagingQuery(model);
		return result;
	}

	/** 
	* @Description: 获取详细信息
	* @param id
	* @param @return  BaseEntity
	* @return  
	* @throws 
	*/ 
	@ApiOperation(value="TChapter-获取详细信息", notes="根据id获取详细信息")
	@RequestMapping(value = "/getDetail",method = RequestMethod.GET)
	@Check({"id:required#主键ID"})
	public BaseEntity getDetail(@RequestParam Long id) throws Exception
	{
		BaseEntity result = iTChapterService.getDetail(id);
		return result;
	}

	/**
	* @throws Exception  
	* @Description: 新增
	* @param TChapter
	* @return BaseEntity 
	* @throws 
	*/ 
	@ApiOperation(value="TChapter-新增数据", notes="新增数据")
	@RequestMapping(value = "/insert",method = RequestMethod.POST)
	@MethodLog(module="TChapter",remark="新增",operateType=Constants.OPERATE_TYPE_ADD)
	@Check({"volume:required#卷;length#卷#50","chapter:required#章;length#章#50","section:required#节;length#节#50","intro:required#章节内容;length#章节内容#65535",})
	public BaseEntity insert(@RequestBody TChapter model) throws Exception
	{
		BaseEntity result = iTChapterService.insert(model);
		return result;
	}

	/**
	* @throws Exception  
	* @Description: 更新
	* @param TChapter
	* @return  BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="TChapter-更新数据", notes="更新数据")
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	@MethodLog(module="TChapter",remark="更新",operateType=Constants.OPERATE_TYPE_UPDATE)
	@Check({"id:required#主键ID","volume:required#卷;length#卷#50","chapter:required#章;length#章#50","section:required#节;length#节#50","intro:required#章节内容;length#章节内容#65535",})
	public BaseEntity update(@RequestBody TChapter model) throws Exception
	{
		BaseEntity result = iTChapterService.update(model);
		return result;
	}

	/** 
	* @Description: 单条删除
	* @param id
	* @return  BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="TChapter-单条删除数据", notes="根据主键删除数据")
	@RequestMapping(value = "/delete",method = RequestMethod.GET)
	@MethodLog(module="TChapter",remark="单条删除",operateType=Constants.OPERATE_TYPE_DELETE)
	@Check({"id:required#主键ID"})
	public BaseEntity delete(@RequestParam Long id)
	{
		BaseEntity result = iTChapterService.delete(id);
		return result;
	}

	/** 
	* @Description: 批量删除
	* @param TChapter 
	* @return BaseEntity 
	* @throws 
	*/ 
	@ApiOperation(value="TChapter-批量删除", notes="批量删除数据")
	@RequestMapping(value = "/deletebatch",method = RequestMethod.POST)
	@MethodLog(module="TChapter",remark="批量删除",operateType=Constants.OPERATE_TYPE_BATCHDELETE)
	public BaseEntity deletebatch(@RequestBody List<Long> ids)
	{
		BaseEntity result = iTChapterService.deleteBatch(ids);
		return result;
	}
}
