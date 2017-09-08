package com.study.springcloud.rabbitmq.controller;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.springcloud.rabbitmq.vo.PaymentNotifyVo;


@RestController
public class Sender{
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
		PaymentNotifyVo notify = new PaymentNotifyVo();
		notify.setOrderNo("201709081053");
		notify.setAmount(new BigDecimal(10));
		notify.setStatus(0);
		CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());  
		rabbitTemplate.convertSendAndReceive("spring-boot-exchange","spring-boot-routingKey",notify,correlationId);
		return "消息发送成功";
	}


}