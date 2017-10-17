//package com.study.springcloud.excel.utils;
//
//import java.io.BufferedReader;
//import java.io.FileInputStream;
//import java.io.InputStreamReader;
//
//
///**
// * 关于获取maven项目中resources下的资源
// * @author admin
// *
// */
//public class UrlUtils {
//	
//	public static void main(String[] args) {
//		
//		//由于resouces下面的资源 compile后其实是在classes
//		ClassLoader classLoader = UrlUtils.class.getClassLoader();
//		String filepath = classLoader.getResource("spring/application.xml").getPath();
//		
//		System.out.println("配置文件路径为:"+filepath);
//		
//		try {
//			BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(filepath)));
//			String content = null;
//			StringBuffer sb = new StringBuffer();
//			while((content = bf.readLine())!= null){
//				sb.append(content);
//			}
//			System.out.println("配置文件内容为:"+sb.toString());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		
//	}
//
//}
