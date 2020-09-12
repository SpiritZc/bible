/** 
 * 模块：圣经-TweetCollect
 */
package com.moon.bible.controller.tweetcollect;

import java.util.List;

import com.moon.bible.annotation.LoginUser;
import com.moon.bible.base.UserInfoDto;
import com.moon.bible.entity.tweetdetails.TweetDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.moon.bible.base.BaseController;
import com.moon.bible.base.BaseEntity;
import com.moon.bible.constants.Constants;
import com.moon.bible.entity.tweetcollect.TweetCollect;
import com.moon.bible.annotation.Check;
import com.moon.bible.annotation.MethodLog;
import com.moon.bible.api.tweetcollect.ITweetCollectService;
import io.swagger.annotations.ApiOperation;
 /**  
* @Description: TweetCollectcontroller类
* @author 
* @date 2020-09-04 01:00:30
* @version V1.0  
 */
@RestController
@RequestMapping("/api/tweetCollect")
public class TweetCollectController extends BaseController {

	/**
	 * iTweetCollectService服务注入
	 */
	@Autowired
	private ITweetCollectService iTweetCollectService;

	/** 
	* @Description: 分页查询表格
	* @param TweetCollect
	* @param @return  
	* @return BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="TweetCollect-收藏推文", notes="收藏推文")
	@RequestMapping(value = "/collectTweet",method = RequestMethod.GET)
	@MethodLog(module="TweetCollect",remark="收藏推文",operateType=Constants.OPERATE_TYPE_ADD)
	@Check({"tweetDteailsId:required#推文Id"})
	public BaseEntity collectTweet(@RequestParam Long tweetDteailsId, @LoginUser UserInfoDto userInfoDto)
	{
		
		BaseEntity result = new BaseEntity();
		result = iTweetCollectService.collectTweet(tweetDteailsId,userInfoDto);
		return result;
	}


	 /**
	  * @Description: 分页查询表格
	  * @param TweetCollect
	  * @param @return
	  * @return BaseEntity
	  * @throws
	  */
	 @ApiOperation(value="TweetCollect-个人收藏推文列表", notes="个人收藏推文列表")
	 @RequestMapping(value = "/getCollectList",method = RequestMethod.GET)
	 @MethodLog(module="TweetCollect",remark="个人收藏推文列表",operateType=Constants.OPERATE_TYPE_ADD)
	 public List<TweetDetails> getCollectList(@LoginUser UserInfoDto userInfoDto)
	 {
		 List<TweetDetails> tweetDetails = iTweetCollectService.getCollectList(userInfoDto);
		 return tweetDetails;
	 }

	 /**
	  * @Description: 单条删除
	  * @param id
	  * @return  BaseEntity
	  * @throws
	  */
	 @ApiOperation(value="TweetCollect-单条删除数据", notes="根据主键删除数据")
	 @RequestMapping(value = "/delete",method = RequestMethod.GET)
	 @MethodLog(module="TweetCollect",remark="单条删除",operateType=Constants.OPERATE_TYPE_DELETE)
	 @Check({"tweetDteailsId:required#详情ID"})
	 public BaseEntity delete(@RequestParam Long tweetDteailsId,@LoginUser UserInfoDto userInfoDto)
	 {
		 BaseEntity result = iTweetCollectService.delete(tweetDteailsId,userInfoDto);
		 return result;
	 }

	 /**
	  * @Method collectCount
	  * @Author zhangcheng
	  * @Version  1.0
	  * @Description 查询收藏总条数
	  * @Return
	  * @Exception
	  * @Date 2020-9-10 10:37
	  */
	 @ApiOperation(value="TweetCollect-查询收藏总条数", notes="查询收藏总条数")
	 @RequestMapping(value = "/collectCount",method = RequestMethod.GET)
	 @MethodLog(module="TweetCollect",remark="查询收藏总条数",operateType=Constants.OPERATE_TYPE_SEARCH)
	 public Integer collectCount(@LoginUser UserInfoDto userInfoDto){
		 return iTweetCollectService.collectCount(userInfoDto);
	 }

}
