package com.moon.bible.config;

import com.alibaba.fastjson.parser.ParserConfig;
import com.moon.bible.base.FastJson2JsonRedisSerializer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport{

	@Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
		RedisSerializer<String> redisSerializer = new StringRedisSerializer();

        // 配置序列化（解决乱码的问题）,过期时间30秒
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(30))
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))//设置key序列化器
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(fastJson2JsonRedisSerializer()))//设置value序列化器
                .disableCachingNullValues(); //如果是空值，不缓存

        RedisCacheManager cacheManager = RedisCacheManager.builder(factory)
                .cacheDefaults(config)
                .build();
        return cacheManager;
    }
	@Bean
    @SuppressWarnings("rawtypes")
    public RedisSerializer fastJson2JsonRedisSerializer() {
        //开启fastjson autotype功能（不开启，造成EntityWrapper<T>中的T无法正常解析）
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        return new FastJson2JsonRedisSerializer<Object>(Object.class);
    }

    @Bean
    @SuppressWarnings("rawtypes")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory, RedisSerializer fastJson2JsonRedisSerializer) {
    	RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
    	template.setConnectionFactory(factory);
        template.setValueSerializer(fastJson2JsonRedisSerializer);
        template.setHashValueSerializer(fastJson2JsonRedisSerializer);
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }
}
