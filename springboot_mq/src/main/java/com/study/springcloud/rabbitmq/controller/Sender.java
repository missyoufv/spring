package com.study.springcloud.rabbitmq.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Sender {
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	
	/**
	 * 构造方法注入
	 */
	@Autowired
	public Sender(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	@RequestMapping("/send")
	public String sendMsg() {
		Object response = rabbitTemplate.convertSendAndReceive("spring-boot-exchange","spring-boot-routingKey","啊q正传");
//		rabbitTemplate.convertAndSend("spring-boot-exchange", "spring-boot-routingKey", "啊q正传");
//		System.out.println("响应报文:"+response);
		return "消息发送成功";
	}

}