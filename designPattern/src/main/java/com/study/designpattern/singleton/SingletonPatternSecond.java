package com.study.designpattern.singleton;

/**
 * 饿汉模式 线程安全的 类加载机制避免了同步问题
 * @author admin
 *
 */
public class SingletonPatternSecond {
	private static SingletonPatternSecond instance = new SingletonPatternSecond();
	
	public static SingletonPatternSecond getInstance(){
		return instance;
	}

}
