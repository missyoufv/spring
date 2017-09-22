package com.study.designpattern.singleton;

/**
 * 第二种单例模式
 * @author admin
 *
 */
public class SingletonPatternOne {
	
	private SingletonPatternOne(){}
	
	public static SingletonPatternOne getInstance(){
        return SingletonInstance.instance;
    }
    
    private static class SingletonInstance{
        
        static SingletonPatternOne instance = new SingletonPatternOne();
        
    }
	
}
