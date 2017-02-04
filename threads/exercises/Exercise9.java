package threads.exercises;

/**
 * p665
 * 定制可以设置优先级的ThreadFactory
 */

import java.util.concurrent.*;

class PriorityThreadFactory implements ThreadFactory {
	private int priority = Thread.NORM_PRIORITY;

	public PriorityThreadFactory(int priority) {
		this.priority = priority;
	}

	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r);
		t.setPriority(priority);
		return t;
	}
}


class SimplePriorites implements Runnable {
	private int countDown = 5;
	private volatile double d;

	public String toString() {
		return Thread.currentThread() + "  " + countDown;
	}

	public void run() {
		while(countDown-- > 0) {
			for(int i = 0; i < 10_000_000; i++) {
				d += (Math.PI + Math.E) / (double)i;
				if(i % 1000 == 0) {
					Thread.yield();
				}
			}
			System.out.println(this);
		}
	}
}

public class Exercise9 {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool(
			new PriorityThreadFactory(Thread.MIN_PRIORITY));
		for(int i = 0; i < 3; i++) {
			exec.execute(new SimplePriorites());
		}
		Thread.yield();
		exec.shutdown();

		exec = Executors.newCachedThreadPool(
			new PriorityThreadFactory(Thread.MAX_PRIORITY));
		exec.execute(new SimplePriorites());
		Thread.yield();
		exec.shutdown();

	}
}