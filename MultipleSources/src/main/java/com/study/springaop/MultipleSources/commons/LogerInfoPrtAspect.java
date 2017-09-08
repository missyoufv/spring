package com.study.springaop.MultipleSources.commons;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.study.springaop.MultipleSources.commons.annotation.LogerInfoPrt;


@Aspect
@Component
public class LogerInfoPrtAspect {
	
	
	@Before("@annotation(logerInfoPrt)")
	public void loggerInfoPrt( LogerInfoPrt logerInfoPrt){
		Object ret = null;
		try {
			if(logerInfoPrt.loggerPrt()){
				System.out.println("日志切面加入成功!");
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
