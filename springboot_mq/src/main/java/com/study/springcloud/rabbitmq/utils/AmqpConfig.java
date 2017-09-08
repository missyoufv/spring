package com.study.springcloud.rabbitmq.utils;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AmqpConfig {

	public static final String EXCHANGE = "spring-boot-exchange";
	public static final String ROUTINGKEY = "spring-boot-routingKey";

	@Bean
	public ConnectionFactory connectionFactory() {
		//集成springboot config模块 可以从配置服务中心获取配置 
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setAddresses("127.0.0.1:5672");
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");
		connectionFactory.setVirtualHost("/");
	 
       // connectionFactory.setPublisherReturns(true);  
        /** 
         * 同样一个RabbitTemplate只支持一个ConfirmCallback。 
         * 对于发布确认，template要求CachingConnectionFactory的publisherConfirms属性设置为true。 
         * 如果客户端通过setConfirmCallback(ConfirmCallback callback)注册了RabbitTemplate.ConfirmCallback，消息确认消费 将回调生产者。 
         * 这个回调函数必须实现以下方法： 
         * void confirm(CorrelationData correlationData, booleanack); 
         */  
		connectionFactory.setPublisherConfirms(true);
		return connectionFactory;
	}
	
    
	/**
	 * 如果需要在生产者需要消息发送后的回调，需要对rabbitTemplate设置ConfirmCallback对象，
	 * 由于不同的生产者需要对应不同的ConfirmCallback，如果rabbitTemplate设置为单例bean，
	 * 则所有的rabbitTemplate实际的ConfirmCallback为最后一次申明的ConfirmCallback。
	 */

	
    
	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public RabbitTemplate rabbitTemplate() {
		RabbitTemplate template = new RabbitTemplate(connectionFactory());
		template.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
			
			@Override
			public void confirm(CorrelationData correlationData, boolean ack,
					String cause) {
				System.out.println(" 回调id:" + correlationData);  
		        if (ack) {  
		            System.out.println("消息成功消费");  
		        } else {  
		            System.out.println("消息消费失败:" + cause);  
		        }  
				
			}
		});
		return template;
	}

	@Bean
	public DirectExchange defaultExchange() {
		return new DirectExchange(EXCHANGE);
	}

	@Bean
	public Queue queue() {
		return new Queue("spring-boot-queue", true);

	}
	
	//对交换机和消息队列进行绑定
	@Bean
	public Binding binding() {
		return BindingBuilder.bind(queue()).to(defaultExchange())
				.with(AmqpConfig.ROUTINGKEY);
	}


}