package threads;

import threads.util.DaemonThreadFactory;
import java.util.concurrent.*;

public class DaemonFromFactory implements Runnable {

	@Override
	public String toString() {
		return Thread.currentThread() + " " + super.toString();
	}

	@Override
	public void run() {
		try{
			while(true) {
				TimeUnit.MILLISECONDS.sleep(300);
				System.out.println(this);
			}
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool(
			new DaemonThreadFactory());  // 通过指定工厂来确定创建线程的方法

		for(int i = 0; i < 3; i++) {
			exec.execute(new DaemonFromFactory());
		}
		exec.shutdown();
		System.out.println("All daemon started");

		TimeUnit.SECONDS.sleep(2);

	}
}