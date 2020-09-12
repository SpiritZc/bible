package com.moon.bible.impl.tweetcollect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.moon.bible.base.UserInfoDto;
import com.moon.bible.entity.tweetcollect.TweetCollect;
import com.moon.bible.entity.tweetdetails.TweetDetails;
import com.moon.bible.mapper.tweetcollect.TweetCollectMapper;
import com.moon.bible.api.tweetcollect.ITweetCollectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import com.moon.bible.util.MessageUtil;
import com.github.pagehelper.PageHelper;
import com.moon.bible.base.BaseEntity;
import com.moon.bible.base.PageEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.moon.bible.enums.DelFlagEnum;

 /**  
* @Description: TweetCollect服务实现
* @author 
* @date 2020-09-04 01:00:30
* @version V1.0  
 */
@Service
public class TweetCollectServiceImpl extends ServiceImpl<TweetCollectMapper, TweetCollect> implements ITweetCollectService {
  
	/** 
	* @Title: tablePagingQuery 
	* @Description: 表格分页查询
	* @param @param model
	* @return BaseEntity 
	* @author 
	* @throws 
	*/ 
	@Override
	public PageEntity tablePagingQuery(TweetCollect model) {
		PageEntity result = new PageEntity();
		model.setDelFlag(DelFlagEnum.UNDEL.getCode());
		com.github.pagehelper.Page<?> page = PageHelper.startPage(model.getCurrentPage(), model.getPageSize()); //分页条件
		List<TweetCollect> list = this.baseMapper.searchDataLike(model);
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
	public BaseEntity insert(TweetCollect model) {
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
	public BaseEntity update(TweetCollect model) {
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
	public BaseEntity delete(Long tweetDteailsId,UserInfoDto userInfoDto) {
		TweetCollect tweetCollect = new TweetCollect();
		UpdateWrapper<TweetCollect> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("tweetdetails_id",tweetDteailsId);
		updateWrapper.eq("user_id",userInfoDto.getUserId());
		tweetCollect.setDelFlag(DelFlagEnum.DEL.getCode());
		this.update(tweetCollect,updateWrapper);
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
		List<TweetCollect> list = new ArrayList<TweetCollect>();
		for (int i = 0; i < ids.size(); i++) {
			TweetCollect tweetCollect = new TweetCollect();
			tweetCollect.setId(ids.get(i));
			tweetCollect.setDelFlag(DelFlagEnum.DEL.getCode());
			list.add(tweetCollect);
		}
		BaseEntity result = new BaseEntity();
		if (list != null && list.size() > 0) {
			this.updateBatchById(list);
		}
		result.setStatusMsg(MessageUtil.getValue("info.delete"));
		return result;
	}

	/**
	 * @Method collectTweet
	 * @Author zhangcheng
	 * @Version  1.0
	 * @Description 收藏推文

	 * @Return
	 * @Exception
	 * @Date 2020-9-4 13:06
	 */
	 @Override
	 public BaseEntity collectTweet(Long tweetDteailsId, UserInfoDto userInfoDto) {
		 BaseEntity result = new BaseEntity();
	 	//根据user_id和tweetdetails_id 确定一条
		 QueryWrapper<TweetCollect> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("user_id",userInfoDto.getUserId());
		 queryWrapper.eq("tweetdetails_id",tweetDteailsId);
		 queryWrapper.eq("del_flag",DelFlagEnum.UNDEL.getCode());
		 TweetCollect one = this.getOne(queryWrapper);
		 if (null==one){
		 	TweetCollect tweetCollect = new TweetCollect();
		 	tweetCollect.setUserId(userInfoDto.getUserId());
		 	tweetCollect.setTweetdetailsId(tweetDteailsId);
		 	tweetCollect.setCollectTime(new Date());
		 	this.save(tweetCollect);
			 result.setStatusMsg(MessageUtil.getValue("info.insert"));
		 }else {
			 one.setCollectTime(new Date());
			 this.update(one);
			 result.setStatusMsg(MessageUtil.getValue("info.updtae"));
		 }
		 return result;
	 }

	 /**
	     * 功能描述: 查询个人推文列表<br>
	     * 〈〉
	     * @Param: [userInfoDto]
	     * @Return: java.util.List<com.moon.bible.entity.tweetdetails.TweetDetails>
	     * @Author: Administrator
	     * @Date: 2020/9/5 13:47
	  */
	 @Override
	 public List<TweetDetails> getCollectList(UserInfoDto userInfoDto) {
		 return this.baseMapper.getCollectList(userInfoDto.getUserId());
	 }

	 /**
	  * @Method collectCount
	  * @Author zhangcheng
	  * @Version  1.0
	  * @Description 查询收藏总数

	  * @Return
	  * @Exception
	  * @Date 2020-9-10 10:35
	  */
	 @Override
	 public Integer collectCount(UserInfoDto userInfoDto) {
		QueryWrapper<TweetCollect> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_id",userInfoDto.getUserId());
		queryWrapper.eq("del_flag",DelFlagEnum.UNDEL.getCode());
		return this.baseMapper.selectCount(queryWrapper);
	 }
 }