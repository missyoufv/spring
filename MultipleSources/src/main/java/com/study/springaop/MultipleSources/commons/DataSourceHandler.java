package com.study.springaop.MultipleSources.commons;

public class DataSourceHandler {
	
	static ThreadLocal<DataSourceType> DataSourceTypes = new ThreadLocal<DataSourceType>();
	
	public static DataSourceType getDataSourceType(){
        return DataSourceTypes.get();
    }
    public static void setDatasourceType(DataSourceType datasourceType){
    	DataSourceTypes.set(datasourceType);
    }
    
    public static void removeDatasourceType(){
    	DataSourceTypes.remove();
    }

}
