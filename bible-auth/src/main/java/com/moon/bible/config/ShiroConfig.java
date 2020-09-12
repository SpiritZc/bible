
package com.moon.bible.config;

import com.moon.bible.filter.JWTAdminFilter;
import com.moon.bible.filter.JWTAppletsFilter;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: ShiroConfig
 * @Description: shiro配置
 * @Author 蔡阳
 * @DateTime 2019年10月24日13:45:04
 */
@Configuration
public class ShiroConfig {
	
	
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultSecurityManager securityManager,
			@Qualifier("chainDefinitionSectionMetaSource") ChainDefinitionSectionMetaSource chainDefinitionSectionMetaSource) throws Exception {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		
		// 添加自己的过滤器并且取名为jwt
		Map<String, Filter> filterMap = new HashMap<>();
		filterMap.put("jwt", new JWTAdminFilter());
		filterMap.put("jwt_applets", new JWTAppletsFilter());
		shiroFilterFactoryBean.setFilters(filterMap);
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		shiroFilterFactoryBean.setUnauthorizedUrl("/api/unnauthorized");//未授权跳转url
		shiroFilterFactoryBean.setFilterChainDefinitionMap(chainDefinitionSectionMetaSource.getObject());
		return shiroFilterFactoryBean;
	}
	
	@Bean(name = "securityManager")
	public DefaultSecurityManager getDefaultSecurityManager(@Qualifier("myRelam") MyRealm myRealm) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(myRealm);
		return securityManager;
	}
	
	@Bean(name="myRelam")
	public MyRealm getMyRealm() {
		return new MyRealm();
	}
	
	 /**
     * 下面的代码是添加注解支持
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        // 强制使用cglib，防止重复代理和可能引起代理出错的问题
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
    
    @Bean(name = "chainDefinitionSectionMetaSource")
    public ChainDefinitionSectionMetaSource chainDefinitionSectionMetaSource () {
    	return new ChainDefinitionSectionMetaSource();
    }
}
