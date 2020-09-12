/** 
 * 模块：bible-TBook
 */
package com.moon.bible.controller.tbook;

import java.util.List;

import com.moon.bible.dto.tbook.TBookQueryDto;
import com.moon.bible.entity.tchapter.TChapter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.moon.bible.base.BaseController;
import com.moon.bible.base.BaseEntity;
import com.moon.bible.constants.Constants;
import com.moon.bible.entity.tbook.TBook;
import com.moon.bible.annotation.Check;
import com.moon.bible.annotation.MethodLog;
import com.moon.bible.api.tbook.ITBookService;
import io.swagger.annotations.ApiOperation;
 /**  
* @Description: TBookcontroller类
* @author 
* @date 2020-07-26 05:37:41
* @version V1.0  
 */
@RestController
@RequestMapping("/api/tBook")
public class TBookController extends BaseController {

	/**
	 * iTBookService服务注入
	 */
	@Autowired
	private ITBookService iTBookService;

	/** 
	* @Description: 分页查询表格
	* @param TBook
	* @param @return  
	* @return BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="TBook-获取表格数据", notes="获取表格数据")
	@RequestMapping(value = "/getTableList",method = RequestMethod.POST)
	public BaseEntity getTableList(@RequestBody TBookQueryDto model)
	{
		
		BaseEntity result = new BaseEntity();
		result = iTBookService.tablePagingQueryByTime(model);
		return result;
	}

	/** 
	* @Description: 获取详细信息
	* @param id
	* @param @return  BaseEntity
	* @return  
	* @throws 
	*/ 
	@ApiOperation(value="TBook-获取详细信息", notes="根据id获取详细信息")
	@RequestMapping(value = "/getDetail",method = RequestMethod.GET)
	@Check({"id:required#主键ID"})
	public BaseEntity getDetail(@RequestParam Long id) throws Exception
	{
		BaseEntity result = iTBookService.getDetail(id);
		return result;
	}

	/**
	* @throws Exception  
	* @Description: 新增
	* @param TBook
	* @return BaseEntity 
	* @throws 
	*/ 
	@ApiOperation(value="TBook-新增数据", notes="新增数据")
	@RequestMapping(value = "/insert",method = RequestMethod.POST)
	@MethodLog(module="TBook",remark="新增",operateType=Constants.OPERATE_TYPE_ADD)
	@Check({"bookName:required#书籍名称;length#书籍名称#100","author:required#作者;length#作者#100","publishing:required#出版社;length#出版社#255"})
	public BaseEntity insert(@RequestBody TBook model) throws Exception
	{
		BaseEntity result = iTBookService.insert(model);
		return result;
	}

	/**
	* @throws Exception  
	* @Description: 更新
	* @param TBook
	* @return  BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="TBook-更新数据", notes="更新数据")
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	@MethodLog(module="TBook",remark="更新",operateType=Constants.OPERATE_TYPE_UPDATE)
	@Check({"id:required#主键ID","bookName:required#书籍名称;length#书籍名称#100","author:required#作者;length#作者#100","publishing:required#出版社;length#出版社#255"})
	public BaseEntity update(@RequestBody TBook model) throws Exception
	{
		BaseEntity result = iTBookService.update(model);
		return result;
	}

	/** 
	* @Description: 单条删除
	* @param id
	* @return  BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="TBook-单条删除数据", notes="根据主键删除数据")
	@RequestMapping(value = "/delete",method = RequestMethod.GET)
	@MethodLog(module="TBook",remark="单条删除",operateType=Constants.OPERATE_TYPE_DELETE)
	@Check({"id:required#主键ID"})
	public BaseEntity delete(@RequestParam Long id)
	{
		BaseEntity result = iTBookService.delete(id);
		return result;
	}

	/** 
	* @Description: 批量删除
	* @param TBook 
	* @return BaseEntity 
	* @throws 
	*/ 
	@ApiOperation(value="TBook-批量删除", notes="批量删除数据")
	@RequestMapping(value = "/deletebatch",method = RequestMethod.POST)
	@MethodLog(module="TBook",remark="批量删除",operateType=Constants.OPERATE_TYPE_BATCHDELETE)
	public BaseEntity deletebatch(@RequestBody List<Long> ids)
	{
		BaseEntity result = iTBookService.deleteBatch(ids);
		return result;
	}

	 /**
	  * @Title: updateStatus
	  * @Description: 账号启用停用
	  * @param userAccount
	  * @return
	  * @author caiyang
	  * @date 2020-06-23 07:48:08
	  */
	 @ApiOperation(value="TBook-上架下架", notes="上架下架")
	 @RequestMapping(value = "/updateStatue",method = RequestMethod.POST)
	 @MethodLog(module="TBook",remark="上架下架",operateType=Constants.OPERATE_TYPE_UPDATE)
	 @Check({"id:required#主键id","state:required#状态"})
//	 @NoRepeatSubmit
	 public BaseEntity updateStatue(@RequestBody TBook tBook) {

		 return this.iTBookService.updateStatus(tBook);
	 }

//	 public TChapter getContentBy
}
