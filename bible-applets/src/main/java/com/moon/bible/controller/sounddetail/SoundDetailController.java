/** 
 * 模块：bible-SoundDetail
 */
package com.moon.bible.controller.sounddetail;

import com.moon.bible.annotation.Check;
import com.moon.bible.annotation.MethodLog;
import com.moon.bible.api.sounddetail.ISoundDetailService;
import com.moon.bible.base.BaseController;
import com.moon.bible.base.BaseEntity;
import com.moon.bible.constants.Constants;
import com.moon.bible.entity.sounddetail.SoundDetail;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
   @Check({"categorySecondId:required#二级分类ID"})
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

}
