package com.moon.bible.api.tweethistory;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moon.bible.base.UserInfoDto;
import com.moon.bible.entity.tweethistory.TweetHistory;
import com.moon.bible.base.BaseEntity;
import com.moon.bible.base.GenericService;
import com.moon.bible.base.PageEntity;

 /**  
* @Description: TweetHistory服务接口
* @author 
* @date 2020-09-05 02:29:43
* @version V1.0  
 */
public interface ITweetHistoryService extends IService<TweetHistory> ,GenericService<TweetHistory>{

	/** 
	* @Title: tablePagingQuery 
	* @Description: 表格分页查询
	* @param @param model
	* @return BaseEntity 
	* @throws 
	*/ 
	PageEntity tablePagingQuery(TweetHistory model);

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
	BaseEntity insert(TweetHistory model);
	
	/**
	*<p>Title: update</p>
	*<p>Description: 更新数据</p>
	* @author caiyang
	* @param model
	*/
	BaseEntity update(TweetHistory model);
	
	/**
	*<p>Title: delete</p>
	*<p>Description: 单条删除数据</p>
	* @author caiyang
	* @param model
	* @return
	*/
	BaseEntity delete(Long id);
	
	/**
	*<p>Title: deleteBatch</p>
	*<p>Description: 批量删除数据</p>
	* @author caiyang
	* @param list
	* @return
	*/
	BaseEntity deleteBatch(List<Long> ids);

	/**
	    * 功能描述: 根据推文id和日期查询历史记录<br>
	    * 〈〉
	    * @Param: [map]
	    * @Return: com.moon.bible.entity.tweethistory.TweetHistory
	    * @Author: Administrator
	    * @Date: 2020/9/5 14:59
	 */
	 TweetHistory getHistoryByDate(Map<String, Object> map);

	 List<Map<String, Object>> getHistoryList(UserInfoDto userInfoDto);

	 /**
	  * @Method delete
	  * @Author zhangcheng
	  * @Version  1.0
	  * @Description 删除历史

	  * @Return
	  * @Exception
	  * @Date 2020-9-10 10:38
	  */
     BaseEntity delete(Long tweetDteailsId, UserInfoDto userInfoDto);

     /**
      * @Method collectCount
      * @Author zhangcheng
      * @Version  1.0
      * @Description

      * @Return
      * @Exception
      * @Date 2020-9-10 10:38
      */
     Integer historyCount(UserInfoDto userInfoDto);
 }
