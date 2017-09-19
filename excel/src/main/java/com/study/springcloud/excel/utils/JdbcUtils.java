package com.study.springcloud.excel.utils;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Properties;
import java.util.ResourceBundle;

public class JdbcUtils {
	
	//初始连接数
	private static int initConnCount=5;
	
	//通过 linkList充当 池
	private static LinkedList<Connection> pool = new LinkedList<Connection>();
	
	private static String  driverClass = null;
	private static String url = null;
	private static String username = null;
	private static String password = null;
	
	static{
		ResourceBundle  bundle = ResourceBundle.getBundle("jdbc");
        driverClass = bundle.getString("mysql.jdbc.driver");
        url = bundle.getString("mysql.jdbc.url");
        username = bundle.getString("mysql.jdbc.username");
        password = bundle.getString("mysql.jdbc.password");
	}
	
	public JdbcUtils(){
	  for (int i = 0; i < initConnCount; i++) {
        //创建 连接
        Connection connection = conn();
        //把 创建的连接 放入池子中
        pool.add(connection);
       }
	}
	
	//建立连接
	private static Connection conn(){
		try{
			//注册驱动
			Class.forName(driverClass);
			return DriverManager.getConnection(url, username, password);
		}catch(Exception ex){
			System.out.println("获取数据库连接异常"+ex);
			return null;
		}
	}
	
	//提供连接
	public static Connection getConnection(){
	 //首先判断 池是否为空
   	 if(pool.isEmpty()){
            //为空的话  继续创建 5个连接
            for (int i = 0; i < 5; i++) {
                Connection connection = conn();
                pool.add(connection);
            }
        }
        //有连接的话  就取出第一个
        Connection con = pool.removeFirst();
        System.out.println("取得一个连接 使用");
        return con;
	}
	
	//释放资源
	public static void release(ResultSet rs,Statement stat,Connection con){
		 //存在连接或结果集的时候 释放
        if(rs!=null){
            try {
                rs.close();
            } catch (Exception e) {
                // TODO: handle exception
            }
            rs= null;
        }
        if(stat!=null){
            try {
                stat.close();
            } catch (Exception e) {
                // TODO: handle exception
            }
            stat = null;
        }
        if(con!=null){
            try {
                pool.add(con);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

}
