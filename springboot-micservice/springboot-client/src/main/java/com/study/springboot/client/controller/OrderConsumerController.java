package com.study.springboot.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderConsumerController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping("/orderInfo")
	public String getOrderInfo(String orderNo){
		orderNo = "1702230000858152";
		//服务名称
		String serverUrl = "http://server-provider/order/queryByNo/{orderNo}";
		String resp =   restTemplate.getForObject(serverUrl, String.class, orderNo);
		System.out.println("查询结果为:"+resp);
		return resp;
	}
}
