package com.study.springcloud.rabbitmq.utils;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setAddresses("127.0.0.1:5672");
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");
		connectionFactory.setVirtualHost("/");
		connectionFactory.setPublisherConfirms(true); // 必须要设置
		return connectionFactory;
	}
	
    
    
	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public RabbitTemplate rabbitTemplate() {
		RabbitTemplate template = new RabbitTemplate(connectionFactory());
		return template;
	}

	@Bean
	public DirectExchange defaultExchange() {
		return new DirectExchange(EXCHANGE);
	}

	@Bean
	public Queue queue() {
		return new Queue("RT_CORP", true); // 实时业务队列

	}

	@Bean
	public Binding binding() {
		return BindingBuilder.bind(queue()).to(defaultExchange())
				.with(AmqpConfig.ROUTINGKEY);
	}


}