package com.moon.bible.config;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**  
 * @ClassName: MybatisPlusConfig
 * @Description: mybatis-plus配置
 * @author caiyang
 * @date 2020-05-29 10:31:35 
*/  
@EnableTransactionManagement
@Configuration
@MapperScan("com.baiyyy.yfz.mapper.*")
public class MybatisPlusConfig {
    /** 
    * @Title: paginationInterceptor 
    * @Description: 分页插件
    * @param @return 设定文件 
    * @return PaginationInterceptor 返回类型 
    * @throws 
    */ 
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
    
    /**  
     * @Title: globalConfig
     * @Description: 自动填充功能
     * @return
     * @author caiyang
     * @date 2020-05-29 10:31:51 
     */ 
    @Bean
    public GlobalConfig globalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setMetaObjectHandler(new MetaHandler());
        return globalConfig;
    }
}
