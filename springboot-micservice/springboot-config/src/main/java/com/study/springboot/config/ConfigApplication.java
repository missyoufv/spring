package com.study.springboot.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 注册服务中心 对配置文件进行统一的管理 ，方便系统中对url的统一管理与维护。
 */
@EnableConfigServer
@SpringBootApplication
public class ConfigApplication 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(ConfigApplication.class, args);
    }
}
