package com.moon.bible;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName : Application  //类名
 * @Description :   //描述
 * @Author : HTB  //作者
 * @Date: 2020-07-19 16:42  //时间
 */
@SpringBootApplication
@MapperScan("com.moon.bible.mapper")
@EnableSwagger2
public class BibleApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(BibleApplication.class,args);
    }

    @Override//为了打包springboot项目
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }
}
