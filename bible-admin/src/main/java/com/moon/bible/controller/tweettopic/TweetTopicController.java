/** 
 * 模块：bible-TweetTopic
 */
package com.moon.bible.controller.tweettopic;

import java.util.List;

import com.moon.bible.dto.tweettopic.TweetTopicAndDetails;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.moon.bible.base.BaseController;
import com.moon.bible.base.BaseEntity;
import com.moon.bible.constants.Constants;
import com.moon.bible.entity.tweettopic.TweetTopic;
import com.moon.bible.annotation.Check;
import com.moon.bible.annotation.MethodLog;
import com.moon.bible.api.tweettopic.ITweetTopicService;
import io.swagger.annotations.ApiOperation;
 /**  
* @Description: TweetTopiccontroller类
* @author 
* @date 2020-08-23 06:48:47
* @version V1.0  
 */
@RestController
@RequestMapping("/api/tweetTopic")
public class TweetTopicController extends BaseController {

	/**
	 * iTweetTopicService服务注入
	 */
	@Autowired
	private ITweetTopicService iTweetTopicService;

	/** 
	* @Description: 分页查询表格
	* @param TweetTopic
	* @param @return  
	* @return BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="TweetTopic-获取表格数据", notes="获取表格数据")
	@RequestMapping(value = "/getTableList",method = RequestMethod.POST)
	public BaseEntity getTableList(@RequestBody TweetTopic model)
	{
		
		BaseEntity result = new BaseEntity();
		result = iTweetTopicService.tablePagingQuery(model);
		return result;
	}

	/** 
	* @Description: 获取详细信息
	* @param id
	* @param @return  BaseEntity
	* @return  
	* @throws 
	*/ 
	@ApiOperation(value="TweetTopic-获取详细信息", notes="根据id获取详细信息")
	@RequestMapping(value = "/getDetail",method = RequestMethod.GET)
	@Check({"id:required#主键ID"})
	public BaseEntity getDetail(@RequestParam Long id) throws Exception
	{
		BaseEntity result = iTweetTopicService.getDetail(id);
		return result;
	}

	/**
	* @throws Exception  
	* @Description: 新增
	* @param TweetTopic
	* @return BaseEntity 
	* @throws 
	*/ 
	@ApiOperation(value="TweetTopic-新增数据", notes="新增数据")
	@RequestMapping(value = "/insert",method = RequestMethod.POST)
	@MethodLog(module="TweetTopic",remark="新增",operateType=Constants.OPERATE_TYPE_ADD)
	@Check({"topicName:required#专题名;length#专题名#50",})
	public BaseEntity insert(@RequestBody TweetTopic model) throws Exception
	{
		BaseEntity result = iTweetTopicService.insert(model);
		return result;
	}

	/**
	* @throws Exception  
	* @Description: 更新
	* @param TweetTopic
	* @return  BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="TweetTopic-更新数据", notes="更新数据")
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	@MethodLog(module="TweetTopic",remark="更新",operateType=Constants.OPERATE_TYPE_UPDATE)
	@Check({"id:required#主键ID","topicName:required#专题名;length#专题名#50",})
	public BaseEntity update(@RequestBody TweetTopic model) throws Exception
	{
		BaseEntity result = iTweetTopicService.update(model);
		return result;
	}

	/** 
	* @Description: 单条删除
	* @param id
	* @return  BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="TweetTopic-单条删除数据", notes="根据主键删除数据")
	@RequestMapping(value = "/delete",method = RequestMethod.GET)
	@MethodLog(module="TweetTopic",remark="单条删除",operateType=Constants.OPERATE_TYPE_DELETE)
	@Check({"id:required#主键ID"})
	public BaseEntity delete(@RequestParam Long id)
	{
		BaseEntity result = iTweetTopicService.delete(id);
		return result;
	}

	/** 
	* @Description: 批量删除
	* @param TweetTopic 
	* @return BaseEntity 
	* @throws 
	*/ 
	@ApiOperation(value="TweetTopic-批量删除", notes="批量删除数据")
	@RequestMapping(value = "/deletebatch",method = RequestMethod.POST)
	@MethodLog(module="TweetTopic",remark="批量删除",operateType=Constants.OPERATE_TYPE_BATCHDELETE)
	public BaseEntity deletebatch(@RequestBody List<Long> ids)
	{
		BaseEntity result = iTweetTopicService.deleteBatch(ids);
		return result;
	}

	@ApiOperation(value="TweetTopic-获取推文专题带详情", notes="获取推文专题带详情")
	@RequestMapping(value = "/getTweetTopicAndDetails",method = RequestMethod.GET)
	public List<TweetTopicAndDetails> getTweetTopicAndDetails(Integer size){
		List<TweetTopicAndDetails> tweetTopicAndDetails = iTweetTopicService.getTweetTopicAndDetails(size);
		return tweetTopicAndDetails;
	}
}
