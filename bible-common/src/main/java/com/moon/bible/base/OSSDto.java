package com.moon.bible.base;

import com.aliyun.oss.model.OSSObject;
import lombok.Data;

/**
 * @ClassName : OSSDto  //类名
 * @Description : oss拓展dto  //描述
 * @Author : HTB  //作者
 * @Date: 2020-08-06 20:12  //时间
 */
@Data
public class OSSDto extends OSSObject {
    private String content;//文章内容
}
