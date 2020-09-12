package com.moon.bible.mapper.tweettopic;
import com.moon.bible.base.GenericMapper;
import com.moon.bible.dto.tweettopic.TweetTopicAndDetails;
import com.moon.bible.entity.tweettopic.TweetTopic;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @Description: TweetTopicMapper类
* @author 
* @date 2020-08-23 06:48:47
* @version V1.0  
 */
public interface TweetTopicMapper extends BaseMapper<TweetTopic> ,GenericMapper<TweetTopic>{

     List<TweetTopicAndDetails> getTweetTopicAndDetails(@Param("size") Integer size);
 }
