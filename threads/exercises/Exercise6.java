package threads.exercises;

/**
 * p660
 * 创建几个随机休眠任务
 */

import java.util.*;
import java.util.concurrent.*;

class Sleep implements Runnable {
	private int milliseconds;

	public Sleep(int ms) {
		System.out.println(Thread.currentThread() + "   thread will start");
		this.milliseconds = ms;
	}

	@Override
	public void run() {
		try {
			// TimeUnit.SECONDS.sleep(time);
			Thread.sleep(milliseconds);
			System.out.println(Thread.currentThread() + " : sleep " + milliseconds + " ms");
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}


public class Exercise6 {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		Random r = new Random();
		for(int i = 0; i < 5; i++) {
			exec.execute(new Sleep((int)(r.nextDouble() * 10 * 1000))); //前闭后开
		}
		exec.shutdown();
	}
}