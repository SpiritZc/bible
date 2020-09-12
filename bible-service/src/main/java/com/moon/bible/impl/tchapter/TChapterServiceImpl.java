package com.moon.bible.impl.tchapter;

import com.moon.bible.entity.tchapter.TChapter;
import com.moon.bible.mapper.tchapter.TChapterMapper;
import com.moon.bible.api.tchapter.ITChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import com.moon.bible.util.MessageUtil;
import com.github.pagehelper.PageHelper;
import com.moon.bible.base.BaseEntity;
import com.moon.bible.base.PageEntity;
import java.util.ArrayList;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.moon.bible.enums.DelFlagEnum;

 /**  
* @Description: TChapter服务实现
* @author 
* @date 2020-08-22 08:31:37
* @version V1.0  
 */
@Service
public class TChapterServiceImpl extends ServiceImpl<TChapterMapper, TChapter> implements ITChapterService {
  
	/** 
	* @Title: tablePagingQuery 
	* @Description: 表格分页查询
	* @param @param model
	* @return BaseEntity 
	* @author 
	* @throws 
	*/ 
	@Override
	public PageEntity tablePagingQuery(TChapter model) {
		PageEntity result = new PageEntity();
		model.setDelFlag(DelFlagEnum.UNDEL.getCode());
		com.github.pagehelper.Page<?> page = PageHelper.startPage(model.getCurrentPage(), model.getPageSize()); //分页条件
		List<TChapter> list = this.baseMapper.searchDataLike(model);
		result.setData(list);
		result.setTotal(page.getTotal());
		result.setCurrentPage(model.getCurrentPage());
		result.setPageSize(model.getPageSize());
		return result;
	}


	/**
	*<p>Title: getDetail</p>
	*<p>Description: 获取详情</p>
	* @author 
	* @param id
	* @return
	*/
	@Override
	public BaseEntity getDetail(Long id) {
		return this.getById(id);
	}

	/**
	*<p>Title: insert</p>
	*<p>Description: 新增数据</p>
	* @author 
	* @param model
	* @return
	*/
	@Transactional
	@Override
	public BaseEntity insert(TChapter model) {
		BaseEntity result = new BaseEntity();
		this.save(model);
		result.setStatusMsg(MessageUtil.getValue("info.insert"));
		return result;
	}

	/**
	*<p>Title: update</p>
	*<p>Description: 更新数据</p>
	* @author 
	* @param model
	* @return
	*/
	@Transactional
	@Override
	public BaseEntity update(TChapter model) {
		BaseEntity result = new BaseEntity();
		this.updateById(model);
		result.setStatusMsg(MessageUtil.getValue("info.update"));
		return result;
	}

	/**
	*<p>Title: delete</p>
	*<p>Description: 单条删除数据</p>
	* @author 
	* @param model
	* @return
	*/
	@Transactional
	@Override
	public BaseEntity delete(Long id) {
		TChapter tChapter = new TChapter();
		tChapter.setId(id);
		tChapter.setDelFlag(DelFlagEnum.DEL.getCode());
		this.updateById(tChapter);
		BaseEntity result = new BaseEntity();
		result.setStatusMsg(MessageUtil.getValue("info.delete"));
		return result;
	}

	/**
	*<p>Title: deleteBatch</p>
	*<p>Description: 批量删除数据</p>
	* @author 
	* @param list
	* @return
	*/
	@Transactional
	@Override
	public BaseEntity deleteBatch(List<Long> ids) {
		List<TChapter> list = new ArrayList<TChapter>();
		for (int i = 0; i < ids.size(); i++) {
			TChapter tChapter = new TChapter();
			tChapter.setId(ids.get(i));
			tChapter.setDelFlag(DelFlagEnum.DEL.getCode());
			list.add(tChapter);
		}
		BaseEntity result = new BaseEntity();
		if (list != null && list.size() > 0) {
			this.updateBatchById(list);
		}
		result.setStatusMsg(MessageUtil.getValue("info.delete"));
		return result;
	}

	/**
	    * 功能描述: 删除旧的章节数据<br>
	    * 〈〉
	    * @Param: [id]
	    * @Return: void
	    * @Author: Administrator
	    * @Date: 2020/8/23 13:32
	 */
	 @Override
	 public void deletOldChapter(Long id) {
		this.baseMapper.deletOldChapter(id);
	 }
 }