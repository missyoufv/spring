package com.study.dubbo.task;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class StudyJob extends QuartzJobBean{

	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		JobDataMap map = context.getMergedJobDataMap();
		System.out.println(map.get("pattern"));
		System.out.println("hello world ,task job!"+System.currentTimeMillis());
	}
	
	

}
