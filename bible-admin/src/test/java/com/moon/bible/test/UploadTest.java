//package com.moon.bible.test;
//
//import com.aliyun.oss.model.OSSObject;
//import com.google.common.io.CharStreams;
//import com.moon.bible.BibleApplication;
//import com.moon.bible.api.common.IUploadService;
//import com.moon.bible.base.OSSDto;
//import com.moon.bible.util.CheckUtil;
//import org.apache.commons.codec.Charsets;
//import org.apache.commons.lang3.StringUtils;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.*;
//import java.util.stream.Collectors;
//
///**
// * @ClassName : UploadTest  //类名
// * @Description :   //描述
// * @Author : HTB  //作者
// * @Date: 2020-08-05 21:46  //时间
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = BibleApplication.class)
//public class UploadTest {
//
//    @Autowired
//    private IUploadService iUploadService;
//
//    @Test
//    public void test1() throws IOException {
////        OSSDto file = iUploadService.getFile("https://peinipet.oss-cn-beijing.aliyuncs.com/bible/text/Bible.txt");
////        String content = file.getContent();
////        if (StringUtils.isNoneBlank(content)){
////            //按照换行符拆分
////            String[] split = content.split("\r\n");
////            //存放所有书籍
////            Map<String,List<String>> map = new HashMap();
////            //每一卷存放内容list
////            List<String> list = null;
////            String volume = "";
////            int times = 0;//记录有多少卷
////            for (int i = 0;i<split.length;i++){
////                //如果不是数字说明是每一卷的开头
////                System.out.println(i);
////                if (!CheckUtil.isNumber(split[i].substring(0,1))){
////                    //存放每一卷所有的行,重新赋值一个list
////                    //吧之前的list加入到map里
////                    //第一次不计
////                    if (times>0) {
////                        map.put(volume, list);
////                    }
////                    list = new ArrayList<>();
////                    list.add(split[i]);
////                    //保存卷名
////                    volume = split[i].trim();
////                    times++;
////                }else {
////                    //分章节
////                    list.add(split[i]);
////                }
////                //最后一卷也要保存
////                if (i == split.length-1){
////                    map.put(volume, list);
////                }
////            }
////            System.out.println(map);
////        }
//
//    }
//
//
//}
