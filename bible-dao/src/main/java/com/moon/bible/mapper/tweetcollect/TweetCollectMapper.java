package com.moon.bible.mapper.tweetcollect;
import com.moon.bible.base.GenericMapper;
import com.moon.bible.entity.tweetcollect.TweetCollect;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moon.bible.entity.tweetdetails.TweetDetails;

import java.util.List;

/**
* @Description: TweetCollectMapper类
* @author 
* @date 2020-09-04 01:00:30
* @version V1.0  
 */
public interface TweetCollectMapper extends BaseMapper<TweetCollect> ,GenericMapper<TweetCollect>{

     List<TweetDetails> getCollectList(Long userId);
 }
