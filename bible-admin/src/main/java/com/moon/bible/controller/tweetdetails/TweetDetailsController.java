/** 
 * 模块：bible-TweetDetails
 */
package com.moon.bible.controller.tweetdetails;

import java.util.List;

import com.moon.bible.annotation.LoginUser;
import com.moon.bible.base.UserInfoDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.moon.bible.base.BaseController;
import com.moon.bible.base.BaseEntity;
import com.moon.bible.constants.Constants;
import com.moon.bible.entity.tweetdetails.TweetDetails;
import com.moon.bible.annotation.Check;
import com.moon.bible.annotation.MethodLog;
import com.moon.bible.api.tweetdetails.ITweetDetailsService;
import io.swagger.annotations.ApiOperation;
 /**  
* @Description: TweetDetailscontroller类
* @author 
* @date 2020-08-23 06:49:01
* @version V1.0  
 */
@RestController
@RequestMapping("/api/tweetDetails")
public class TweetDetailsController extends BaseController {

	/**
	 * iTweetDetailsService服务注入
	 */
	@Autowired
	private ITweetDetailsService iTweetDetailsService;

	/** 
	* @Description: 分页查询表格
	* @param TweetDetails
	* @param @return  
	* @return BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="TweetDetails-获取表格数据", notes="获取表格数据")
	@RequestMapping(value = "/getTableList",method = RequestMethod.POST)
	public BaseEntity getTableList(@RequestBody TweetDetails model)
	{
		
		BaseEntity result = new BaseEntity();
		result = iTweetDetailsService.tablePagingQuery(model);
		return result;
	}

	/** 
	* @Description: 获取详细信息
	* @param id
	* @param @return  BaseEntity
	* @return  
	* @throws 
	*/ 
	@ApiOperation(value="TweetDetails-获取详细信息", notes="根据id获取详细信息")
	@RequestMapping(value = "/getDetail",method = RequestMethod.GET)
	@Check({"id:required#主键ID"})
	public BaseEntity getDetail(@RequestParam Long id, @LoginUser UserInfoDto userInfoDto) throws Exception
	{
		BaseEntity result = iTweetDetailsService.getDetail(id,userInfoDto);
		return result;
	}

	/**
	* @throws Exception  
	* @Description: 新增
	* @param TweetDetails
	* @return BaseEntity 
	* @throws 
	*/ 
	@ApiOperation(value="TweetDetails-新增数据", notes="新增数据")
	@RequestMapping(value = "/insert",method = RequestMethod.POST)
	@MethodLog(module="TweetDetails",remark="新增",operateType=Constants.OPERATE_TYPE_ADD)
	@Check({"topicId:required#专题id","title:required#标题;length#标题#200","fromAuthor:required#文章来源作者;length#文章来源作者#100","content:required#推文内容;length#推文内容#65535","img:required#推文图片路径;length#推文图片路径#500",})
	public BaseEntity insert(@RequestBody TweetDetails model) throws Exception
	{
		BaseEntity result = iTweetDetailsService.insert(model);
		return result;
	}

	/**
	* @throws Exception  
	* @Description: 更新
	* @param TweetDetails
	* @return  BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="TweetDetails-更新数据", notes="更新数据")
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	@MethodLog(module="TweetDetails",remark="更新",operateType=Constants.OPERATE_TYPE_UPDATE)
	@Check({"id:required#主键ID","topicId:required#专题id","title:required#标题;length#标题#200","fromAuthor:required#文章来源作者;length#文章来源作者#100","content:required#推文内容;length#推文内容#65535","img:required#推文图片路径;length#推文图片路径#500",})
	public BaseEntity update(@RequestBody TweetDetails model) throws Exception
	{
		BaseEntity result = iTweetDetailsService.update(model);
		return result;
	}

	/** 
	* @Description: 单条删除
	* @param id
	* @return  BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="TweetDetails-单条删除数据", notes="根据主键删除数据")
	@RequestMapping(value = "/delete",method = RequestMethod.GET)
	@MethodLog(module="TweetDetails",remark="单条删除",operateType=Constants.OPERATE_TYPE_DELETE)
	@Check({"id:required#主键ID"})
	public BaseEntity delete(@RequestParam Long id)
	{
		BaseEntity result = iTweetDetailsService.delete(id);
		return result;
	}

	/** 
	* @Description: 批量删除
	* @param TweetDetails 
	* @return BaseEntity 
	* @throws 
	*/ 
	@ApiOperation(value="TweetDetails-批量删除", notes="批量删除数据")
	@RequestMapping(value = "/deletebatch",method = RequestMethod.POST)
	@MethodLog(module="TweetDetails",remark="批量删除",operateType=Constants.OPERATE_TYPE_BATCHDELETE)
	public BaseEntity deletebatch(@RequestBody List<Long> ids)
	{
		BaseEntity result = iTweetDetailsService.deleteBatch(ids);
		return result;
	}
}
