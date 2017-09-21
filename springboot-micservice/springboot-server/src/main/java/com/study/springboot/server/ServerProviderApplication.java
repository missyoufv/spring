package com.study.springboot.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 *	服务提供方
 */
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.study.springboot.server.dao")
public class ServerProviderApplication 
{
    public static void main( String[] args )
    {
        SpringApplication.run(ServerProviderApplication.class, args);
    }
}
