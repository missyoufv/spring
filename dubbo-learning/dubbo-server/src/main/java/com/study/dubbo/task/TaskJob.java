//package com.study.dubbo.task;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//
//import com.study.dubbo.service.impl.CacheService;
//
//@EnableScheduling
//@Configuration
//public class TaskJob {
//
//	@Autowired
//	private CacheService cacheService;
//	
//	@Scheduled(cron = "0 0/1 * * * ?") // 每分钟执行一次
//	public void run(){
//		cacheService.deleteAll();
//		System.out.println("定时清空redis缓存........");
//	}
//}
