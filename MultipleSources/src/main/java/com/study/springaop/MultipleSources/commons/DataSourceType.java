package com.study.springaop.MultipleSources.commons;

public enum DataSourceType {
	
	test("test"),
	test1("test1");
	
	DataSourceType(String dataSourceType){
		this.dataSourceType = dataSourceType;
	}
	private String dataSourceType;
	
	
	public String getDataSourceType() {
		return dataSourceType;
	}
	
}
