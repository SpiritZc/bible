package com.moon.bible.impl.userfeedback;

import com.moon.bible.entity.userfeedback.UserFeedback;
import com.moon.bible.mapper.userfeedback.UserFeedbackMapper;
import com.moon.bible.api.userfeedback.IUserFeedbackService;
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
* @Description: UserFeedback服务实现
* @author 
* @date 2020-09-12 12:17:50
* @version V1.0  
 */
@Service
public class UserFeedbackServiceImpl extends ServiceImpl<UserFeedbackMapper, UserFeedback> implements IUserFeedbackService {
  
	/** 
	* @Title: tablePagingQuery 
	* @Description: 表格分页查询
	* @param @param model
	* @return BaseEntity 
	* @author 
	* @throws 
	*/ 
	@Override
	public PageEntity tablePagingQuery(UserFeedback model) {
		PageEntity result = new PageEntity();
		model.setDelFlag(DelFlagEnum.UNDEL.getCode());
		com.github.pagehelper.Page<?> page = PageHelper.startPage(model.getCurrentPage(), model.getPageSize()); //分页条件
		List<UserFeedback> list = this.baseMapper.searchDataLike(model);
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
	public BaseEntity insert(UserFeedback model) {
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
	public BaseEntity update(UserFeedback model) {
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
		UserFeedback userFeedback = new UserFeedback();
		userFeedback.setId(id);
		userFeedback.setDelFlag(DelFlagEnum.DEL.getCode());
		this.updateById(userFeedback);
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
		List<UserFeedback> list = new ArrayList<UserFeedback>();
		for (int i = 0; i < ids.size(); i++) {
			UserFeedback userFeedback = new UserFeedback();
			userFeedback.setId(ids.get(i));
			userFeedback.setDelFlag(DelFlagEnum.DEL.getCode());
			list.add(userFeedback);
		}
		BaseEntity result = new BaseEntity();
		if (list != null && list.size() > 0) {
			this.updateBatchById(list);
		}
		result.setStatusMsg(MessageUtil.getValue("info.delete"));
		return result;
	}
}