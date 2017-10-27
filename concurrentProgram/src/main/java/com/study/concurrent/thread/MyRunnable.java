package com.study.concurrent.thread;

public class MyRunnable implements Runnable {

	private int ticket = 5;

	@Override
	public synchronized void run() {
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (; ticket > 0; ticket--) {
			System.out.println("线程" + Thread.currentThread().getId() + "出售票"
					+ ticket);
		}
	}

	public static void main(String[] args) {
		MyRunnable my = new MyRunnable();
		new Thread(my).start();
		new Thread(my).start();
		new Thread(my).start();
	}

}
