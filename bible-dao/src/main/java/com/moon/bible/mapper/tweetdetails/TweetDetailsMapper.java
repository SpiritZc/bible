package com.moon.bible.mapper.tweetdetails;
import com.moon.bible.base.GenericMapper;
import com.moon.bible.entity.tweetdetails.TweetDetails;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* @Description: TweetDetailsMapper类
* @author 
* @date 2020-08-23 06:57:04
* @version V1.0  
 */
public interface TweetDetailsMapper extends BaseMapper<TweetDetails> ,GenericMapper<TweetDetails>{
     List<TweetDetails> selectByTopicIdAndSize(Map<String,Object> map);
}
