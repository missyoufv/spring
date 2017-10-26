package com.study.JdbcPool;

public class PoolTest {
	
	public static void main(String[] args) {
		System.out.println(MyPool.getConnection().getBusy());
	}

}
