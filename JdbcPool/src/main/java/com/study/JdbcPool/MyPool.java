package com.study.JdbcPool;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义数据库连接池
 * @author admin
 *
 */
public class MyPool {
	
	private static Logger logger  = LoggerFactory.getLogger(MyPool.class);

	private static String driverClass;
	
	private static String url;
	
	private static String userName;
	
	private static String password;
	
	//最大连接数
	private static int poolMaxSize=100;
	
	//每次新加连接数
	private static int stepCount=2;
	
	//初始连接数（最小连接数）
	private static int initSize=3;
	
	
	//连接池
	private static List<JdbcConnection> pool = new ArrayList<JdbcConnection>();
	
	//私有构造函数
	private MyPool(){
	}
	
	/**
	 * 读取配置文件，加载驱动
	 */
	static{
		
		try{
			InputStream inputStream = new FileInputStream(MyPool.class.getClassLoader().getResource("jdbc.properties").getPath());
			Properties  properties = new Properties();
			properties.load(inputStream);
			driverClass = properties.getProperty("mysql.jdbc.driver");
			url = properties.getProperty("mysql.jdbc.url");
			userName = properties.getProperty("mysql.jdbc.username");
			password = properties.getProperty("mysql.jdbc.password");
			
			createConnection(initSize);
		}catch(Exception ex){
			logger.error("加载配置文件异常:{}", ex);
		}
	}
	
	

	private static void createConnection(int count) {
		if(pool.size() + count > poolMaxSize){
			logger.error("连接数已达到容器能容纳的最大值");
			throw new RuntimeException("连接数已达到容器能容纳的最大值");
		}
		try{
			Class.forName(driverClass);
			for(int i = 0 ;i<count ; i++){
				Connection conn = DriverManager.getConnection(url, userName, password);
				JdbcConnection poolConn = new JdbcConnection(conn,false);
				pool.add(poolConn);
			}
			logger.info("创建连接成功，连接数"+count);
		}catch(Exception ex){
			logger.error("获取数据库连接异常",ex);
		}
	}

	
	private synchronized static JdbcConnection getRealConnection(){
		
		for(JdbcConnection conn : pool){
			if(!conn.getBusy()){
				try{
					Connection connection = conn.getConnection();
					if(!connection.isValid(3000)){
						connection = DriverManager.getConnection(url, userName, password);
						conn.setConnection(connection);
					}
					conn.setBusy(true);
					return conn;
				}catch(Exception ex){
					logger.error("获取连接异常:{}",ex);
					return null;
				}
			}
		}
		
		return null;
	}
	
	/**
	 * 获取连接
	 * @return
	 */
	public synchronized static JdbcConnection getConnection(){
		if(pool.isEmpty()){
			createConnection(stepCount);
		}
		
		JdbcConnection conn = getRealConnection();
		while(conn == null){
			try{
				createConnection(stepCount);
				conn = getRealConnection();
				Thread.sleep(3000);
			}catch(Exception ex){
				logger.info("获取连接异常:{}",ex);
			}
		}
		return conn;
		
	}
	
	
	
	
}
