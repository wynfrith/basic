package threads.exercises;

/**
 * p665
 * 创建后台线程, 并有它派生几个子线程..
 * Experiment with different sleep times in
 * Daemons.java to see what happens 
 *
 * 结论: 如果主线程已经停止执行那么daemon线程会强行终止.
 *
 */

import java.util.concurrent.*;
public class Exercise7 {
	public static void main(String[] args) throws InterruptedException{
		if(args.length != 1) {
			System.err.println("Sleep time needs to be provided in msecs");
			return;
		}
		int sleepTime = Integer.parseInt(args[0]);

		Thread d = new Thread(new Daemon());
		d.setDaemon(true);
		d.start();

		System.out.println("d.isDaemon? " + d.isDaemon());
		// TimeUnit.SECONDS.sleep(2);
		TimeUnit.MILLISECONDS.sleep(sleepTime);
	}
}

class Daemon implements Runnable {
	private Thread[] threads = new Thread[10];

	@Override
	public void run() {
		for(int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new SpawnDaemon());
			threads[i].start();
			System.out.println("DaemonSpawn " + i + " started");
		}

		try { TimeUnit.MILLISECONDS.sleep(100); } catch(InterruptedException e) { /*ignore*/ }
		
		for(int i = 0; i < threads.length; i++) {
			System.out.println("threads[" + i + "].isDaemon() = " + threads[i].isDaemon());
		}

		while(true) {
			Thread.yield();
		}
	}

}

class SpawnDaemon implements Runnable {
	@Override
	public void run() {
		while(true) {
			Thread.yield();
		}	
	}
}