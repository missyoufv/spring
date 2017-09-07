package com.study.springcloud.rabbitmq.controller;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerController {
	
	@RabbitListener(queues = "spring-boot-queue")
	public String process(String msg){
		System.out.println("接收到消息" + msg);
		return "qaq";
	}
	
}
