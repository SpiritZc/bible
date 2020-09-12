package com.moon.bible.api.tweettopic;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.moon.bible.base.UserInfoDto;
import com.moon.bible.dto.tweettopic.TweetTopicAndDetails;
import com.moon.bible.entity.tweettopic.TweetTopic;
import com.moon.bible.base.BaseEntity;
import com.moon.bible.base.GenericService;
import com.moon.bible.base.PageEntity;

 /**  
* @Description: TweetTopic服务接口
* @author 
* @date 2020-08-23 06:48:47
* @version V1.0  
 */
public interface ITweetTopicService extends IService<TweetTopic> ,GenericService<TweetTopic>{

	/** 
	* @Title: tablePagingQuery 
	* @Description: 表格分页查询
	* @param @param model
	* @return BaseEntity 
	* @throws 
	*/ 
	PageEntity tablePagingQuery(TweetTopic model);

	/**
	*<p>Title: getDetail</p>
	*<p>Description: 获取详情</p>
	* @author caiyang
	* @param id
	* @return
	*/
	BaseEntity getDetail(Long id);
	
	/**
	*<p>Title: insert</p>
	*<p>Description: 新增数据</p>
	* @author caiyang
	* @param model
	* @return
	*/
	BaseEntity insert(TweetTopic model);
	
	/**
	*<p>Title: update</p>
	*<p>Description: 更新数据</p>
	* @author caiyang
	* @param model
	*/
	BaseEntity update(TweetTopic model);

	 public BaseEntity delete(Long id);

	
	/**
	*<p>Title: deleteBatch</p>
	*<p>Description: 批量删除数据</p>
	* @author caiyang
	* @param list
	* @return
	*/
	BaseEntity deleteBatch(List<Long> ids);

	/**
	    * 功能描述: 查询专题和详情<br>
	    * 〈〉
	    * @Param: [size]
	    * @Return: java.util.List<com.moon.bible.dto.tweettopic.TweetTopicAndDetails>
	    * @Author: Administrator
	    * @Date: 2020/8/30 13:43
	 */
	List<TweetTopicAndDetails> getTweetTopicAndDetails(Integer size);

 }
