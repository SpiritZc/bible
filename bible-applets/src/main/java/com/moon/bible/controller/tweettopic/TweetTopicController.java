/** 
 * 模块：bible-TweetTopic
 */
package com.moon.bible.controller.tweettopic;

import com.moon.bible.annotation.Check;
import com.moon.bible.annotation.LoginUser;
import com.moon.bible.annotation.MethodLog;
import com.moon.bible.api.tweettopic.ITweetTopicService;
import com.moon.bible.base.BaseController;
import com.moon.bible.base.BaseEntity;
import com.moon.bible.base.UserInfoDto;
import com.moon.bible.constants.Constants;
import com.moon.bible.dto.tweettopic.TweetTopicAndDetails;
import com.moon.bible.entity.tweettopic.TweetTopic;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    * @Method getTweetTopicAndDetails
    * @Author zhangcheng
    * @Version  1.0
    * @Description 获取推文专题带详情
    * @Return
    * @Exception
    * @Date 2020-9-3 16:30
    */
   @ApiOperation(value="TweetTopic-获取推文专题带详情", notes="获取推文专题带详情")
   @RequestMapping(value = "/getTweetTopicAndDetails",method = RequestMethod.GET)
   public List<TweetTopicAndDetails> getTweetTopicAndDetails(Integer size){
       List<TweetTopicAndDetails> tweetTopicAndDetails = iTweetTopicService.getTweetTopicAndDetails(size);
       return tweetTopicAndDetails;
   }


}
