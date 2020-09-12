package com.moon.bible.api.tweetcollect;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.moon.bible.annotation.LoginUser;
import com.moon.bible.base.UserInfoDto;
import com.moon.bible.entity.tweetcollect.TweetCollect;
import com.moon.bible.base.BaseEntity;
import com.moon.bible.base.GenericService;
import com.moon.bible.base.PageEntity;
import com.moon.bible.entity.tweetdetails.TweetDetails;

/**
* @Description: TweetCollect服务接口
* @author 
* @date 2020-09-04 01:00:30
* @version V1.0  
 */
public interface ITweetCollectService extends IService<TweetCollect> ,GenericService<TweetCollect>{

	/** 
	* @Title: tablePagingQuery 
	* @Description: 表格分页查询
	* @param @param model
	* @return BaseEntity 
	* @throws 
	*/ 
	PageEntity tablePagingQuery(TweetCollect model);

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
	BaseEntity insert(TweetCollect model);
	
	/**
	*<p>Title: update</p>
	*<p>Description: 更新数据</p>
	* @author caiyang
	* @param model
	*/
	BaseEntity update(TweetCollect model);
	
	/**
	*<p>Title: delete</p>
	*<p>Description: 单条删除数据</p>
	* @author caiyang
	* @param model
	* @return
	*/
	BaseEntity delete(Long tweetDteailsId,UserInfoDto userInfoDto);
	
	/**
	*<p>Title: deleteBatch</p>
	*<p>Description: 批量删除数据</p>
	* @author caiyang
	* @param list
	* @return
	*/
	BaseEntity deleteBatch(List<Long> ids);

    /**
     * @Method collectTweet
     * @Author zhangcheng
     * @Version  1.0
     * @Description 收藏推文
     * @Return
     * @Exception
     * @Date 2020-9-4 13:06
     */
	BaseEntity collectTweet(Long tweetDteailsId, UserInfoDto userInfoDto);

	/**
	    * 功能描述: 查询个人推文列表<br>
	    * 〈〉
	    * @Param: [userInfoDto]
	    * @Return: java.util.List<com.moon.bible.entity.tweetdetails.TweetDetails>
	    * @Author: Administrator
	    * @Date: 2020/9/5 13:46
	 */
     List<TweetDetails> getCollectList(UserInfoDto userInfoDto);

     /**
      * @Method collectCount
      * @Author zhangcheng
      * @Version  1.0
      * @Description 查询收藏总条数

      * @Return
      * @Exception
      * @Date 2020-9-10 10:33
      */
    Integer collectCount(UserInfoDto userInfoDto);
}
