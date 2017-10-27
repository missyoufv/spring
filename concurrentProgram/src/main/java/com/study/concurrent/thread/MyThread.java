package com.study.concurrent.thread;

public class MyThread extends Thread {
	
	private  int ticket = 5;
	
	@Override
	public void run() {
		for(;ticket>0;ticket--){
			System.out.println("线程"+Thread.currentThread().getId()+"出售票"+ticket);
		}
	}
	
	public static void main(String[] args) {
		
		new MyThread().start();
		new MyThread().start();
		new MyThread().start();
		
	}

}
