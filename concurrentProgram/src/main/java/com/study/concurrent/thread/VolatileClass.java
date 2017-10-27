package com.study.concurrent.thread;


/**
 * 如果isStop 变量不用volatile修饰，则程序子线程会一直循环
 * 	原因：jdk对线程进行了优化，为了提高效率，为每条线程分配了独立的运行空间，将这个线程所需的资源copy了一份，当线程引擎在执行线程是，并不会去主线程取该变量的
 * 		  值，因此while 会一直循环。
 *  volatile 作用： 是变量在各线程中可见。当该变量的值修改的时候，会强制线程引擎去主线程去该变量值，并替换线程资源中 该变量的值。
 *  	传统实现volatile修饰的变量在多个线程中可见：其实可以用synchronized 对该变量枷锁，多个线程来去的时候，只能等其他线程释放锁资源，因此
 *  	可以实现，当变量值修改的时候，在多个线程中可见。这种效率很低，volatile并不是这种实现方式。		  
 *  
 *  	
 *  volatile 保证数据可见性 然后不能保证数据的原子性
 * 		
 *  what is 原子性？
 *		原子性是拒绝多线程操作的，不论是多核还是单核，具有原子性的量，同一时刻只能有一个线程来对它进行操作。
 * @author admin
 *
 */
public class VolatileClass {
	
	private volatile boolean isStop = true;
	
	public void setStop(boolean isStop) {
		this.isStop = isStop;
	}
	
	public static void main(String[] args) {
		
		final VolatileClass vc = new VolatileClass();
		
		new Thread(new Runnable(){

			@Override
			public void run() {
				
				while(vc.isStop){
					
				}
				
				System.out.println("任务执行完!!!");
			}
			
		}).start();
		
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		vc.setStop(false);
		
		System.out.println("当前任务是否停止"+vc.isStop);
	}

}
