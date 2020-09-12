package com.moon.bible.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * @ClassName : SwaggerDocConfig  //类名
 * @Description : swagger配置  //描述
 * @Author : HTB  //作者
 * @Date: 2020-07-19 15:21  //时间
 */
@Configuration
public class SwaggerDocConfig {
    /**
     * @Fields swagger_is_enable : 是否激活swagger
     * @author caiyang
     * @date 2020年5月14日
     */
    @Value("${swagger.is.enable}")
    private boolean swagger_is_enable;

    /**
     *<p>Title: createRestApi</p>
     *<p>Description: 全部接口</p>
     * @author caiyang
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("A-全部接口")
                .enable(swagger_is_enable)
                .apiInfo(apiInfo("测试项目","全部接口文档","1.0"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.moon.bible.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(
                        Collections.singletonList(new ParameterBuilder()
                                .name("Authorization")
                                .description("登录校验")
                                .modelRef(new ModelRef("string"))
                                .parameterType("header")
                                .required(false)
                                .build())
                );
    }

    /**
     *<p>Title: createRestApi</p>
     *<p>Description: sysUser对应的接口</p>
     * @author caiyang
     * @return
     */
    @Bean
    public Docket sysUserRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("userAccount")
                .enable(swagger_is_enable)
                .apiInfo(apiInfo("测试项目","用户管理模块","1.0"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.moon.bible.controller.sysuser"))
                .paths(PathSelectors.regex("/api/userAccount.*"))
                .build()
                .globalOperationParameters(
                        Collections.singletonList(new ParameterBuilder()
                                .name("Authorization")
                                .description("登录校验")
                                .modelRef(new ModelRef("string"))
                                .parameterType("header")
                                .required(false)
                                .build())
                );
    }

    private ApiInfo apiInfo(String title, String description, String version) {
        return new ApiInfoBuilder().title(title).description(description)
                .version(version).build();
    }
}
