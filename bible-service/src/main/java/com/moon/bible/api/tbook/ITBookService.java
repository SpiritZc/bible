package com.moon.bible.api.tbook;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.moon.bible.dto.tbook.TBookQueryDto;
import com.moon.bible.entity.tbook.TBook;
import com.moon.bible.base.BaseEntity;
import com.moon.bible.base.GenericService;
import com.moon.bible.base.PageEntity;

 /**  
* @Description: TBook服务接口
* @author 
* @date 2020-07-26 05:37:41
* @version V1.0  
 */
public interface ITBookService extends IService<TBook> ,GenericService<TBook>{

	/** 
	* @Title: tablePagingQuery 
	* @Description: 表格分页查询
	* @param @param model
	* @return BaseEntity 
	* @throws 
	*/ 
	PageEntity tablePagingQuery(TBook model);

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
	BaseEntity insert(TBook model);
	
	/**
	*<p>Title: update</p>
	*<p>Description: 更新数据</p>
	* @author caiyang
	* @param model
	*/
	BaseEntity update(TBook model);
	
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
	 * @Method updateStatus
	 * @Author zhangcheng
	 * @Version  1.0
	 * @Description 修改书籍状态
	 * @Return
	 * @Exception
	 * @Date 2020-7-31 16:22
	 */
	BaseEntity updateStatus(TBook tBook);

	 PageEntity tablePagingQueryByTime(TBookQueryDto model);
 }
