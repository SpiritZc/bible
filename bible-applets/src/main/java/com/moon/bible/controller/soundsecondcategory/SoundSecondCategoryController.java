/** 
 * 模块：bible-SoundSecondCategory
 */
package com.moon.bible.controller.soundsecondcategory;

import com.moon.bible.annotation.Check;
import com.moon.bible.annotation.MethodLog;
import com.moon.bible.api.soundsecondcategory.ISoundSecondCategoryService;
import com.moon.bible.base.BaseController;
import com.moon.bible.base.BaseEntity;
import com.moon.bible.constants.Constants;
import com.moon.bible.entity.soundsecondcategory.SoundSecondCategory;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @Description: SoundSecondCategorycontroller类
* @author 
* @date 2020-09-06 03:43:04
* @version V1.0  
*/
@RestController
@RequestMapping("/api/soundSecondCategory")
public class SoundSecondCategoryController extends BaseController {

   /**
    * iSoundSecondCategoryService服务注入
    */
   @Autowired
   private ISoundSecondCategoryService iSoundSecondCategoryService;

   /**
   * @Description: 分页查询表格
   * @param SoundSecondCategory
   * @param @return
   * @return BaseEntity
   * @throws
   */
   @ApiOperation(value="SoundSecondCategory-获取表格数据", notes="获取表格数据")
   @RequestMapping(value = "/getTableList",method = RequestMethod.POST)
   @Check({"categoryId:required#一级分类ID"})
   public BaseEntity getTableList(@RequestBody SoundSecondCategory model)
   {

       BaseEntity result = new BaseEntity();
       result = iSoundSecondCategoryService.tablePagingQuery(model);
       return result;
   }

   /**
   * @Description: 获取详细信息
   * @param id
   * @param @return  BaseEntity
   * @return
   * @throws
   */
   @ApiOperation(value="SoundSecondCategory-获取详细信息", notes="根据id获取详细信息")
   @RequestMapping(value = "/getDetail",method = RequestMethod.GET)
   @Check({"id:required#主键ID"})
   public BaseEntity getDetail(@RequestParam Long id) throws Exception
   {
       BaseEntity result = iSoundSecondCategoryService.getDetail(id);
       return result;
   }
}
