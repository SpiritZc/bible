package com.moon.bible.api.tchapter;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.moon.bible.entity.tchapter.TChapter;
import com.moon.bible.base.BaseEntity;
import com.moon.bible.base.GenericService;
import com.moon.bible.base.PageEntity;

 /**  
* @Description: TChapter服务接口
* @author 
* @date 2020-08-22 08:31:37
* @version V1.0  
 */
public interface ITChapterService extends IService<TChapter> ,GenericService<TChapter>{

	/** 
	* @Title: tablePagingQuery 
	* @Description: 表格分页查询
	* @param @param model
	* @return BaseEntity 
	* @throws 
	*/ 
	PageEntity tablePagingQuery(TChapter model);

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
	BaseEntity insert(TChapter model);
	
	/**
	*<p>Title: update</p>
	*<p>Description: 更新数据</p>
	* @author caiyang
	* @param model
	*/
	BaseEntity update(TChapter model);
	
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
	    * 功能描述: 删除旧的章节数据<br>
	    * 〈〉
	    * @Param: [id]
	    * @Return: void
	    * @Author: Administrator
	    * @Date: 2020/8/23 13:31
	 */
     void deletOldChapter(Long id);
 }
