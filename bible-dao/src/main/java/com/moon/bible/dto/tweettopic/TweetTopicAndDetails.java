package com.moon.bible.dto.tweettopic;

import com.moon.bible.entity.tcategory.TCategory;
import com.moon.bible.entity.tweetdetails.TweetDetails;
import com.moon.bible.entity.tweettopic.TweetTopic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName : TCategoryAndDtails  //类名
 * @Description : 发现分类带详情  //描述
 * @Author : HTB  //作者
 * @Date: 2020-08-30 13:38  //时间
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TweetTopicAndDetails extends TweetTopic {
    private List<TweetDetails> tweetDetails;//推文详情
}
