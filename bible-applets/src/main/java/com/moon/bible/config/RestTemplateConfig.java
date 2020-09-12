package com.moon.bible.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @ProjectName: bible-project
 * @Package: com.moon.bible.config
 * @ClassName: RestTemplateConfig
 * @Author: Administrator
 * @Description: resttamplet配置类
 * @Date: 2020-9-1 9:43
 * @Version: 1.0
 */
@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        return new RestTemplate(factory);
    }

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(5000);
        factory.setConnectTimeout(5000);
        return factory;
    }
}
