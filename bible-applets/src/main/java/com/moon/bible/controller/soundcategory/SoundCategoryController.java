/** 
 * 模块：bible-SoundCategory
 */
package com.moon.bible.controller.soundcategory;

import com.moon.bible.annotation.Check;
import com.moon.bible.api.soundcategory.ISoundCategoryService;
import com.moon.bible.base.BaseController;
import com.moon.bible.base.BaseEntity;
import com.moon.bible.entity.soundcategory.SoundCategory;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
* @Description: SoundCategorycontroller类
* @author 
* @date 2020-08-26 08:51:23
* @version V1.0  
*/
@RestController
@RequestMapping("/api/soundCategory")
public class SoundCategoryController extends BaseController {

   /**
    * iSoundCategoryService服务注入
    */
   @Autowired
   private ISoundCategoryService iSoundCategoryService;

   /**
   * @Description: 分页查询表格
   * @param SoundCategory
   * @param @return
   * @return BaseEntity
   * @throws
   */
   @ApiOperation(value="SoundCategory-获取表格数据", notes="获取表格数据")
   @RequestMapping(value = "/getTableList",method = RequestMethod.POST)
   public BaseEntity getTableList(@RequestBody SoundCategory model)
   {

       BaseEntity result = new BaseEntity();
       result = iSoundCategoryService.tablePagingQuery(model);
       return result;
   }

   /**
   * @Description: 获取详细信息
   * @param id
   * @param @return  BaseEntity
   * @return
   * @throws
   */
   @ApiOperation(value="SoundCategory-获取详细信息", notes="根据id获取详细信息")
   @RequestMapping(value = "/getDetail",method = RequestMethod.GET)
   @Check({"id:required#主键ID"})
   public BaseEntity getDetail(@RequestParam Long id) throws Exception
   {
       BaseEntity result = iSoundCategoryService.getDetail(id);
       return result;
   }
}
