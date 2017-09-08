package com.study.springaop.MultipleSources.commons;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


public class DynamicDataSource extends AbstractRoutingDataSource {

	private static final Logger LOGGER = LoggerFactory.getLogger(DynamicDataSource.class);
	
	private DataSource master;
	
	private DataSource slave;
	
	
	protected Object determineCurrentLookupKey() {
		return DataSourceHandler.getDataSourceType();
	}
	
	
	
	

	public DynamicDataSource(DataSource master, DataSource slave) {
		this.master = master;
		this.slave = slave;
		Map dataSources = new HashMap();
        dataSources.put(DataSourceType.test, master);
        dataSources.put(DataSourceType.test1, slave);
        this.setTargetDataSources(dataSources);
        this.afterPropertiesSet();
        this.setDefaultTargetDataSource(master);
	}




	public DataSource getMaster() {
		return master;
	}



	public void setMaster(DataSource master) {
		this.master = master;
	}



	public DataSource getSlave() {
		return slave;
	}



	public void setSlave(DataSource slave) {
		this.slave = slave;
	}
	
	
}
