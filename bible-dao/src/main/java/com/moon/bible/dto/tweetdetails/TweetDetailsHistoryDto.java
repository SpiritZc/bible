package com.moon.bible.dto.tweetdetails;

import com.moon.bible.entity.tweetdetails.TweetDetails;
import lombok.Data;

/**
 * @ProjectName: bible-project
 * @Package: com.moon.bible.dto.tweetdetails
 * @ClassName: TweetDetailsHistoryDto
 * @Author: Administrator
 * @Description: 推文历史列表查询dto
 * @Date: 2020-9-7 14:39
 * @Version: 1.0
 */
@Data
public class TweetDetailsHistoryDto extends TweetDetails {
    //分组时间标志
    private String historyDate;
}
