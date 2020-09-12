package com.moon.bible.util;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName : BookSplitUtil  //类名
 * @Description : 书籍分割工具类  //描述
 * @Author : HTB  //作者
 * @Date: 2020-08-22 19:25  //时间
 */
public class BookSplitUtil {
    public static Map<String, List<String>> splitBook(String content){
        Map<String,List<String>> map = new HashMap();
        if (StringUtils.isNoneBlank(content)) {
            //按照换行符拆分
            String[] split = content.split("\r\n");
            //存放所有书籍
            //每一卷存放内容list
            List<String> list = null;
            String volume = "";
            int times = 0;//记录有多少卷
            for (int i = 0; i < split.length; i++) {
                //如果不是数字说明是每一卷的开头
                System.out.println(i);
                if (!CheckUtil.isNumber(split[i].substring(0, 1))) {
                    //存放每一卷所有的行,重新赋值一个list
                    //吧之前的list加入到map里
                    //第一次不计
                    if (times > 0) {
                        map.put(volume, list);
                    }
                    list = new ArrayList<>();
                    list.add(split[i]);
                    //保存卷名
                    volume = split[i].trim();
                    times++;
                } else {
                    //分章节
                    list.add(split[i]);
                }
                //最后一卷也要保存
                if (i == split.length - 1) {
                    map.put(volume, list);
                }
            }
        }
        return map;
    }
}
