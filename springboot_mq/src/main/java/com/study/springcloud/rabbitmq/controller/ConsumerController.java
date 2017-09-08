package com.study.springcloud.rabbitmq.controller;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.study.springcloud.rabbitmq.vo.PaymentNotifyVo;

@Component
@RabbitListener(queues = "spring-boot-queue")
public class ConsumerController {
	
	/**
	 * 监听消息队列 并进行消费
	 * @param msg
	 * @return
	 */
	
	@RabbitHandler  
	public void process(PaymentNotifyVo notifyVo){
		System.out.println("接收到消息,订单信息号为" + notifyVo.getOrderNo());
		System.out.println(notifyVo.toString());
	}
	
}
