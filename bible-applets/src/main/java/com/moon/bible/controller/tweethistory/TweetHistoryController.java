/** 
 * 模块：bible-TweetHistory
 */
package com.moon.bible.controller.tweethistory;

import java.util.List;
import java.util.Map;

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
import com.moon.bible.entity.tweethistory.TweetHistory;
import com.moon.bible.annotation.Check;
import com.moon.bible.annotation.MethodLog;
import com.moon.bible.api.tweethistory.ITweetHistoryService;
import io.swagger.annotations.ApiOperation;
 /**  
* @Description: TweetHistorycontroller类
* @author 
* @date 2020-09-05 02:29:43
* @version V1.0  
 */
@RestController
@RequestMapping("/api/tweetHistory")
public class TweetHistoryController extends BaseController {

	/**
	 * iTweetHistoryService服务注入
	 */
	@Autowired
	private ITweetHistoryService iTweetHistoryService;

	/** 
	* @Description: 分页查询表格
	* @param userInfoDto
	* @param @return  
	* @return BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="TweetHistory-查询推文历史列表", notes="获取表格数据")
	@RequestMapping(value = "/getHistoryList",method = RequestMethod.POST)
	public List<Map<String,Object>> getHistoryList(@LoginUser UserInfoDto userInfoDto)
	{
		List<Map<String,Object>> result = iTweetHistoryService.getHistoryList(userInfoDto);
		return result;
	}

	 /**
	  * @Description: 单条删除
	  * @param id
	  * @return  BaseEntity
	  * @throws
	  */
	 @ApiOperation(value="TweetHistory-单条删除数据", notes="根据主键删除数据")
	 @RequestMapping(value = "/deleteHistory",method = RequestMethod.GET)
	 @MethodLog(module="TweetHistory",remark="单条删除",operateType=Constants.OPERATE_TYPE_UPDATE)
	 @Check({"tweetDteailsId:required#详情ID"})
	 public BaseEntity deleteHistory(@RequestParam Long tweetDteailsId,@LoginUser UserInfoDto userInfoDto)
	 {
		 BaseEntity result = iTweetHistoryService.delete(tweetDteailsId,userInfoDto);
		 return result;
	 }

	 /**
	  * @Method historyCount
	  * @Author zhangcheng
	  * @Version  1.0
	  * @Description 查询七天历史总条数
	  * @Return
	  * @Exception
	  * @Date 2020-9-10 10:37
	  */
	 @ApiOperation(value="TweetHistory-查询七天历史总条数", notes="查询七天历史总条数")
	 @RequestMapping(value = "/historyCount",method = RequestMethod.GET)
	 @MethodLog(module="TweetHistory",remark="查询七天历史总条数",operateType=Constants.OPERATE_TYPE_SEARCH)
	 public Integer historyCount(@LoginUser UserInfoDto userInfoDto){
		 return iTweetHistoryService.historyCount(userInfoDto);
	 }

}
