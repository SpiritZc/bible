package com.moon.bible.impl.tcategory;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.moon.bible.entity.tcategory.TCategory;
import com.moon.bible.mapper.tcategory.TCategoryMapper;
import com.moon.bible.api.tcategory.ITCategoryService;
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
* @Description: TCategory服务实现
* @author 
* @date 2020-07-30 10:22:12
* @version V1.0  
 */
@Service
public class TCategoryServiceImpl extends ServiceImpl<TCategoryMapper, TCategory> implements ITCategoryService {
  
	/** 
	* @Title: tablePagingQuery 
	* @Description: 表格分页查询
	* @param @param model
	* @return BaseEntity 
	* @author 
	* @throws 
	*/ 
	@Override
	public PageEntity tablePagingQuery(TCategory model) {
		PageEntity result = new PageEntity();
		model.setDelFlag(DelFlagEnum.UNDEL.getCode());
		com.github.pagehelper.Page<?> page = PageHelper.startPage(model.getCurrentPage(), model.getPageSize()); //分页条件
		List<TCategory> list = this.baseMapper.searchDataLike(model);
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
	public BaseEntity insert(TCategory model) {
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
	public BaseEntity update(TCategory model) {
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
		TCategory tCategory = new TCategory();
		tCategory.setId(id);
		tCategory.setDelFlag(DelFlagEnum.DEL.getCode());
		this.updateById(tCategory);
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
		List<TCategory> list = new ArrayList<TCategory>();
		for (int i = 0; i < ids.size(); i++) {
			TCategory tCategory = new TCategory();
			tCategory.setId(ids.get(i));
			tCategory.setDelFlag(DelFlagEnum.DEL.getCode());
			list.add(tCategory);
		}
		BaseEntity result = new BaseEntity();
		if (list != null && list.size() > 0) {
			this.updateBatchById(list);
		}
		result.setStatusMsg(MessageUtil.getValue("info.delete"));
		return result;
	}

	 /**
	  * @Method getAllCategory
	  * @Author zhangcheng
	  * @Version  1.0
	  * @Description 查询所有书籍类别
	  * @Return java.util.List<com.moon.bible.entity.tweettopic.TCategory>
	  * @Exception
	  * @Date 2020-7-30 10:37
	  */
	 @Override
	 public List<TCategory> getAllCategory() {
		 List<TCategory> list = new ArrayList<>();
		 QueryWrapper<TCategory> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("del_flag",DelFlagEnum.UNDEL.getCode());
		 return this.baseMapper.selectList(queryWrapper);
	 }
 }