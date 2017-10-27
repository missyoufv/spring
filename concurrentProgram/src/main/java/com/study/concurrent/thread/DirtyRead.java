package com.study.concurrent.thread;

/**
 * 由于没有将操作共享变量的方法加上同步代码块，导致数据脏读
 * 当将一个对象的方法加上同步修饰的时候，要考虑业务的完整性，是否需要在get/set都加上同步修饰，不然容易出现脏读 
 * 
 * 如果在get set方法上同时加上synchronized 就不会有脏读的问题了
 * @author admin
 *
 */
public class DirtyRead {
	
	private int count = 95;
	private String ip ="192.168.0.102";
	
	
	

	public  synchronized void setCountAndIp(int count,String ip){
		this.count = count;
		System.out.println("当前访问数量为"+count);
		
		try{
			Thread.sleep(3000);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		this.ip = ip;
		System.out.println("当前访问用户的ip为"+ip);
	}
	
	public void getCountAndIp(){
		System.out.println("当前访问数量为:"+count+",访问ip为"+ip);
	}
	
	public static void main(String[] args) {
		
		final DirtyRead  dt = new DirtyRead();
		new Thread(new Runnable(){

			@Override
			public void run() {
				dt.setCountAndIp(100,"12.168.0.202");
			}
			
		}).start();
		
		try{
			Thread.sleep(1000);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		dt.getCountAndIp();
	}
}
