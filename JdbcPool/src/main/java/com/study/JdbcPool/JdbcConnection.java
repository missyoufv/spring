package com.study.JdbcPool;

import java.sql.Connection;


public class JdbcConnection {
	
	private Connection connection;
	
	/**
	 * 是否空闲
	 */
	private boolean isBusy;

	public JdbcConnection(Connection connection, boolean isBusy) {
		super();
		this.connection = connection;
		this.isBusy = isBusy;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public boolean getBusy() {
		return isBusy;
	}

	public void setBusy(boolean isBusy) {
		this.isBusy = isBusy;
	}
	
	public void close(){
		this.isBusy =false;
	}

	
	
	
	
	
}
