package com.study.designpattern.singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池应用
 * @author admin
 *
 */
public class ExecutorDemo {
	public static void main(String[] args) throws Exception {
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		for(int i=0;i<1000;i++){
			executorService.execute(new Runnable(){

				@Override
				public void run() {
					try {
						System.out.println("当前运行的线程id为"+Thread.currentThread().getId());
						SingletonPattern.getInstance();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			});
		}
		Thread.sleep(5000);
		executorService.shutdown();
	}

}
