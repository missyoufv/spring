package com.study.springboot.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 注册服务中心
 *
 */
@SpringBootApplication
@EnableEurekaServer
public class  EurekaServer
{
    public static void main( String[] args )
    {
    	SpringApplication.run(EurekaServer.class, args);
    }
}
