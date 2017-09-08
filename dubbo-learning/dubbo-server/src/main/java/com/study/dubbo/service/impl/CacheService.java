package com.study.dubbo.service.impl;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.study.dubbo.api.service.ICacheService;

@Repository
public class CacheService implements ICacheService{

	@Autowired
	private StringRedisTemplate redisTemplate;
	
	@Override
	public Boolean exist(String key){
		return redisTemplate.hasKey(key);
	}
	@Override
	public String get(String key){
		return redisTemplate.opsForValue().get(key);
	}
	@Override
	public void delete(String key){
		redisTemplate.delete(key);
	}
	@Override
	public void put(String key,Object value){
		redisTemplate.opsForValue().set(key, JSON.toJSONString(value));
	}
	@Override
    public Long getExpire(String key){
        return redisTemplate.getExpire(key);
    }
	@Override
    public void put(String key, Object value, long timeout, TimeUnit unit) {
    	redisTemplate.opsForValue().set(key, JSON.toJSONString(value), timeout, unit);
    }
	@Override
	public void deleteAll() {
		Set<String> list = redisTemplate.keys("*");
		for(String obj:list){
			redisTemplate.delete(obj);
		}
	}
}
