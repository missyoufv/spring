package com.study.concurrent.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringLockThread {
	
	private  final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
	private  final String LOCK_PREFIX = "XXX---";
	private String ip;
	
	
	
	public StringLockThread( String ip) {
		this.ip = ip;
	}

	public static void main(String[] args) {
		StringLockThread waitThread1 = new StringLockThread("192.168.0.202");
		StringLockThread waitThread2 = new StringLockThread("192.168.0.202");
		StringLockThread waitThread3 = new StringLockThread("192.168.0.202");
		
		new Thread(waitThread1.new MyRunnable()).start();
		new Thread(waitThread2.new MyRunnable()).start();//内部内的实例化方法
		new Thread(waitThread3.new MyRunnable()).start();
	}
	
	class MyRunnable implements Runnable{

		@Override
		public void run() {
			
			
			String lock = buildLock();
			synchronized (lock) {
				System.out.println("当前执行的线程id为"+Thread.currentThread().getName()+",线程开始时间为"+sdf.format(new Date())+",锁为:"+lock);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("当前执行的线程id为"+Thread.currentThread().getName()+",线程结束时间为"+sdf.format(new Date())+",锁为:"+lock);
			}
		}

		private String buildLock() {
			StringBuffer sb = new StringBuffer();
			sb.append(LOCK_PREFIX).append(ip);
			return sb.toString().intern();
		}
		
	}


}
