package com.study.dubbo.api.service;

import java.util.concurrent.TimeUnit;

public interface ICacheService {
	public Boolean exist(String key);
	public String get(String key);
	public void delete(String key);
	public void put(String key,Object value);
	public Long getExpire(String key);
	public void put(String key, Object value, long timeout, TimeUnit unit);
	public void deleteAll();
}
