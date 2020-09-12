/** 
 * 模块：bible-SoundDetail
 */
package com.moon.bible.controller.sounddetail;

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
import com.moon.bible.entity.sounddetail.SoundDetail;
import com.moon.bible.annotation.Check;
import com.moon.bible.annotation.MethodLog;
import com.moon.bible.api.sounddetail.ISoundDetailService;
import io.swagger.annotations.ApiOperation;
 /**  
* @Description: SoundDetailcontroller类
* @author 
* @date 2020-08-26 08:51:55
* @version V1.0  
 */
@RestController
@RequestMapping("/api/soundDetail")
public class SoundDetailController extends BaseController {

	/**
	 * iSoundDetailService服务注入
	 */
	@Autowired
	private ISoundDetailService iSoundDetailService;

	/** 
	* @Description: 分页查询表格
	* @param SoundDetail
	* @param @return  
	* @return BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="SoundDetail-获取表格数据", notes="获取表格数据")
	@RequestMapping(value = "/getTableList",method = RequestMethod.POST)
	@Check({"categorySecondId:required#主键ID"})
	public BaseEntity getTableList(@RequestBody SoundDetail model)
	{
		
		BaseEntity result = new BaseEntity();
		result = iSoundDetailService.tablePagingQuery(model);
		return result;
	}

	/** 
	* @Description: 获取详细信息
	* @param id
	* @param @return  BaseEntity
	* @return  
	* @throws 
	*/ 
	@ApiOperation(value="SoundDetail-获取详细信息", notes="根据id获取详细信息")
	@RequestMapping(value = "/getDetail",method = RequestMethod.GET)
	@Check({"id:required#主键ID"})
	public BaseEntity getDetail(@RequestParam Long id) throws Exception
	{
		BaseEntity result = iSoundDetailService.getDetail(id);
		return result;
	}

	/**
	* @throws Exception  
	* @Description: 新增
	* @param SoundDetail
	* @return BaseEntity 
	* @throws 
	*/ 
	@ApiOperation(value="SoundDetail-新增数据", notes="新增数据")
	@RequestMapping(value = "/insert",method = RequestMethod.POST)
	@MethodLog(module="SoundDetail",remark="新增",operateType=Constants.OPERATE_TYPE_ADD)
	@Check({"soundName:required#音频文件名;length#音频文件名#100","soundUrl:required#音频路径;length#音频路径#255","soundAuthor:required#音频作者;length#音频作者#100",})
	public BaseEntity insert(@RequestBody SoundDetail model) throws Exception
	{
		BaseEntity result = iSoundDetailService.insert(model);
		return result;
	}

	/**
	* @throws Exception  
	* @Description: 更新
	* @param SoundDetail
	* @return  BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="SoundDetail-更新数据", notes="更新数据")
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	@MethodLog(module="SoundDetail",remark="更新",operateType=Constants.OPERATE_TYPE_UPDATE)
	@Check({"id:required#主键ID","soundName:required#音频文件名;length#音频文件名#100","soundUrl:required#音频路径;length#音频路径#255","soundAuthor:required#音频作者;length#音频作者#100",})
	public BaseEntity update(@RequestBody SoundDetail model) throws Exception
	{
		BaseEntity result = iSoundDetailService.update(model);
		return result;
	}

	/** 
	* @Description: 单条删除
	* @param id
	* @return  BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="SoundDetail-单条删除数据", notes="根据主键删除数据")
	@RequestMapping(value = "/delete",method = RequestMethod.GET)
	@MethodLog(module="SoundDetail",remark="单条删除",operateType=Constants.OPERATE_TYPE_DELETE)
	@Check({"id:required#主键ID"})
	public BaseEntity delete(@RequestParam Long id)
	{
		BaseEntity result = iSoundDetailService.delete(id);
		return result;
	}

	/** 
	* @Description: 批量删除
	* @param SoundDetail 
	* @return BaseEntity 
	* @throws 
	*/ 
	@ApiOperation(value="SoundDetail-批量删除", notes="批量删除数据")
	@RequestMapping(value = "/deletebatch",method = RequestMethod.POST)
	@MethodLog(module="SoundDetail",remark="批量删除",operateType=Constants.OPERATE_TYPE_BATCHDELETE)
	public BaseEntity deletebatch(@RequestBody List<Long> ids)
	{
		BaseEntity result = iSoundDetailService.deleteBatch(ids);
		return result;
	}
}
