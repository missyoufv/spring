package com.study.dubbo.sharding;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

@Service
public class ShardingService {
	
	 private static final Log logger = LogFactory.getLog(ShardingService.class);
	
	public void shardingTradeOrder(){
		try{
			
		}catch(Exception ex){
			logger.error("操作异常", ex);
		}	
	}
}
