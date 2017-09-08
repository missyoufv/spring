package com.study.dubbo.util;


public class DataSourceContextHolder {
	
	private static final ThreadLocal contextHolder = new ThreadLocal();
	
	//设置数据源类型
	public static void setDataSourceType(String dataSourceType){
		contextHolder.set(dataSourceType);
	}
	//获取数据源类型
	public static String getDataSourceType(){
		return (String) contextHolder.get();
	}
	
	//清除数据源类型
	public static void clearDatasourceType(){
		contextHolder.remove();
	}
}
