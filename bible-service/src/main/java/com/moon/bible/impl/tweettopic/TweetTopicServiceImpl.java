package com.moon.bible.impl.tweettopic;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.moon.bible.base.UserInfoDto;
import com.moon.bible.dto.tweettopic.TweetTopicAndDetails;
import com.moon.bible.entity.tweetcollect.TweetCollect;
import com.moon.bible.entity.tweetdetails.TweetDetails;
import com.moon.bible.entity.tweettopic.TweetTopic;
import com.moon.bible.mapper.tweetdetails.TweetDetailsMapper;
import com.moon.bible.mapper.tweettopic.TweetTopicMapper;
import com.moon.bible.api.tweettopic.ITweetTopicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moon.bible.util.MessageUtil;
import com.github.pagehelper.PageHelper;
import com.moon.bible.base.BaseEntity;
import com.moon.bible.base.PageEntity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;
import com.moon.bible.enums.DelFlagEnum;

 /**  
* @Description: TweetTopic服务实现
* @author 
* @date 2020-08-23 06:48:47
* @version V1.0  
 */
@Service
public class TweetTopicServiceImpl extends ServiceImpl<TweetTopicMapper, TweetTopic> implements ITweetTopicService {

	@Autowired
	private TweetDetailsMapper tweetDetailsMapper;
  
	/** 
	* @Title: tablePagingQuery 
	* @Description: 表格分页查询
	* @param @param model
	* @return BaseEntity 
	* @author 
	* @throws 
	*/ 
	@Override
	public PageEntity tablePagingQuery(TweetTopic model) {
		PageEntity result = new PageEntity();
		model.setDelFlag(DelFlagEnum.UNDEL.getCode());
		com.github.pagehelper.Page<?> page = PageHelper.startPage(model.getCurrentPage(), model.getPageSize()); //分页条件
		List<TweetTopic> list = this.baseMapper.searchDataLike(model);
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
	public BaseEntity insert(TweetTopic model) {
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
	public BaseEntity update(TweetTopic model) {
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
		 TweetTopic tweetTopic = new TweetTopic();
		 tweetTopic.setId(id);
		 tweetTopic.setDelFlag(DelFlagEnum.DEL.getCode());
		 this.updateById(tweetTopic);
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
		List<TweetTopic> list = new ArrayList<TweetTopic>();
		for (int i = 0; i < ids.size(); i++) {
			TweetTopic tweetTopic = new TweetTopic();
			tweetTopic.setId(ids.get(i));
			tweetTopic.setDelFlag(DelFlagEnum.DEL.getCode());
			list.add(tweetTopic);
		}
		BaseEntity result = new BaseEntity();
		if (list != null && list.size() > 0) {
			this.updateBatchById(list);
		}
		result.setStatusMsg(MessageUtil.getValue("info.delete"));
		return result;
	}

	 /**
	  * getTweetTopicAndDetails
	  * 查询推文专题和详情
	  * @param size
	  * @return
	  */
	 @Override
	 public List<TweetTopicAndDetails> getTweetTopicAndDetails(Integer size) {
		 QueryWrapper<TweetTopic> tweetTopicQueryWrapper = new QueryWrapper<>();
		 tweetTopicQueryWrapper.eq("del_flag",DelFlagEnum.UNDEL.getCode());
		 List<TweetTopic> tweetTopics = this.baseMapper.selectList(tweetTopicQueryWrapper);
		 List<TweetTopicAndDetails> list = new ArrayList<>();
		 tweetTopics.forEach(p->{
			 TweetTopicAndDetails tweetTopicAndDetails = new TweetTopicAndDetails();
			 BeanUtils.copyProperties(p,tweetTopicAndDetails);
			 list.add(tweetTopicAndDetails);
		 });
		 if (list.size()>0){
			 list.forEach(t->{
			 	Map<String,Object> map = new HashMap<>();
				 map.put("id",t.getId());
				 map.put("size",size);
 				 List<TweetDetails> tweetDetails = tweetDetailsMapper.selectByTopicIdAndSize(map);
				 t.setTweetDetails(tweetDetails);
			 });
		 }
		 return list;
	 }
 }