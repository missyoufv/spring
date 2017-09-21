package com.study.springboot.client.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class JavaMailSenderController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private JavaMailSender mailSender;
	

    @Value("${spring.mail.username}")
    private String username;
	
	@RequestMapping(value="/sendMail")
	public String sendMail(String content){
		try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(username);
            message.setTo("xxx@qq.com");
            message.setSubject("标题：测试标题");
            message.setText("测试内容部份");
            mailSender.send(message);
		}catch(Exception ex){
			logger.info("邮件发送异常",ex);
		}
		return "邮件发送成功";
	}
}
