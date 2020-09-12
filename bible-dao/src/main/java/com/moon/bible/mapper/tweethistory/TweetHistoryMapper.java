package com.moon.bible.mapper.tweethistory;
import com.moon.bible.base.GenericMapper;
import com.moon.bible.dto.tweetdetails.TweetDetailsHistoryDto;
import com.moon.bible.entity.tweetdetails.TweetDetails;
import com.moon.bible.entity.tweethistory.TweetHistory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
* @Description: TweetHistoryMapper类
* @author 
* @date 2020-09-05 02:29:44
* @version V1.0  
 */
public interface TweetHistoryMapper extends BaseMapper<TweetHistory> ,GenericMapper<TweetHistory>{

    /**
        * 功能描述: 根据日期查询历史<br>
        * 〈〉
        * @Param: [map]
        * @Return: com.moon.bible.entity.tweethistory.TweetHistory
        * @Author: Administrator
        * @Date: 2020/9/5 15:40
     */
    TweetHistory getHistoryByDate(Map<String, Object> map);


    /**
     * @Method getHistoryList
     * @Author zhangcheng
     * @Version  1.0
     * @Description 根据用户id查询7天内的历史
     * @Return
     * @Exception
     * @Date 2020-9-7 14:03
     */
    List<TweetDetailsHistoryDto> getHistoryList(Long userId);

    /**
     * @Method getHistoryCount
     * @Author zhangcheng
     * @Version  1.0
     * @Description 查询七天历史记录总数
     * @Return
     * @Exception
     * @Date 2020-9-10 10:41
     */
    Integer getHistoryCount(Long userId);
}
