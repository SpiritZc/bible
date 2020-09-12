package com.moon.bible.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: RedisUtil
 * @Description: redis工具类
 * @author caiyang
 * @date 2020-06-01 08:26:33
 */
@Component
public class RedisUtil {

	/**
	 * @Fields redisTemplate : redisTemplate注入
	 * @author caiyang
	 * @date 2020-06-01 08:28:22
	 */
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	/**  
	 * @Title: hasKey
	 * @Description: 判断key是否存在
	 * @param key
	 * @return
	 * @author caiyang
	 * @date 2020-06-01 08:31:47 
	 */ 
	public boolean hasKey(String key) {
		try {
			return redisTemplate.hasKey(key);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	
	/**  
	 * @Title: del
	 * @Description: 删除缓存，可以传一个值 或多个
	 * @param key
	 * @author caiyang
	 * @date 2020-06-01 08:33:00 
	 */ 
	public void del(String... key) {
		if (key != null && key.length > 0) {
			if (key.length == 1) {
				redisTemplate.delete(key[0]);
			} else {
				redisTemplate.delete(CollectionUtils.arrayToList(key));
			}
		}
	}
	
	/**  
	 * @Title: get
	 * @Description: 普通缓存获取
	 * @param key
	 * @return
	 * @author caiyang
	 * @date 2020-06-01 08:33:38 
	 */ 
	public Object get(String key) {
		return key == null ? null : redisTemplate.opsForValue().get(key);
	}
	
	/**  
	 * @Title: set
	 * @Description: 普通缓存放入
	 * @param key
	 * @param value
	 * @return true成功 false失败
	 * @author caiyang
	 * @date 2020-06-01 08:34:21 
	 */ 
	public boolean set(String key, Object value) {
		try {
			redisTemplate.opsForValue().set(key, value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**  
	 * @Title: set
	 * @Description: 普通缓存放入并设置时间
	 * @param key
	 * @param value
	 * @param time 时间(秒) time要大于0 如果time小于等于0 将设置无限期
	 * @return
	 * @author caiyang
	 * @date 2020-06-01 08:35:02 
	 */ 
	public boolean set(String key, Object value, long time) {
		try {
			if (time > 0) {
				redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
			} else {
				set(key, value);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**  
	 * @Title: getExpire
	 * @Description: 获取key过期时间
	 * @param key
	 * @return
	 * @author caiyang
	 * @date 2020-06-16 09:04:34 
	 */ 
	public Long getExpire(String key) {
		if (StringUtil.isNullOrEmpty(key)) {
			return null;
		}else {
			redisTemplate.getExpire(key);
			return redisTemplate.getExpire(key);
		}
	}
}
