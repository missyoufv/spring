package com.study.designpattern.singleton;


/**
 * 对象锁和类锁 加在静态方法上的是类锁，所有静态方法公用一个锁，非静态方法上的是对象锁，必须是同一个对象，才能保证代码同步
 * 单例设计模式 
 * 怎么保证并发情况下 对象的唯一性了
 * 懒汉模式
 * @author admin
 *
 */
public class SingletonPattern {
	
	private static SingletonPattern singletonPattern;
	
	private SingletonPattern(){
		
	}
	
	/**
	 * 单例方法一
	 * 如果把锁加到方法上，一个线程访问这个方法时，其它所有的线程都要处于挂起等待状态，
	 * 倒是避免了刚才同步访问创造出多个实例的危险，但是这样会造成很多无谓的等待。
	 * @return
	 * @throws Exception
	 */
	public  static SingletonPattern getInstance() throws Exception{
		if(singletonPattern == null){
			synchronized (SingletonPattern.class) {
				if(singletonPattern == null){
					singletonPattern = new SingletonPattern();
					System.out.println("实例化对象的线程id为:"+Thread.currentThread().getId());
				}
			}
		}
		return singletonPattern;
	}
}
