/** 
 * 模块：bible-TweetDetails
 */
package com.moon.bible.controller.tweetdetails;

import com.moon.bible.annotation.Check;
import com.moon.bible.annotation.LoginUser;
import com.moon.bible.annotation.MethodLog;
import com.moon.bible.api.tweetdetails.ITweetDetailsService;
import com.moon.bible.base.BaseController;
import com.moon.bible.base.BaseEntity;
import com.moon.bible.base.UserInfoDto;
import com.moon.bible.constants.Constants;
import com.moon.bible.entity.tweetdetails.TweetDetails;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
   * @param model
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

    @ApiOperation(value="TweetDetails-增加浏览量", notes="增加浏览量")
    @RequestMapping(value = "/increasePageviews",method = RequestMethod.GET)
    @MethodLog(module="TweetDetails",remark="增加浏览量",operateType=Constants.OPERATE_TYPE_ADD)
    @Check({"id:required#主键ID"})
   public BaseEntity increasePageviews(@RequestParam Long id){
        BaseEntity result = iTweetDetailsService.increasePageviews(id);
        return result;
   }

}
