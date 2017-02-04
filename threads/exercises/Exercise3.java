package threads.exercises;

/**
 * p658
 * 用执行器创建多个任务
 */

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class Exercise3 implements Runnable {
	private static int taskCount = 1;

	public static void reset() {
		Exercise3.taskCount = 0;
	}

	private int id = taskCount++;

	private String getId() {
		return Thread.currentThread().toString();
	}

	public Exercise3() {
		
	}

	@Override
	public void run() {
		System.out.println("#" + getId() + " : Started");

		for(int i = 0; i < 2; i++) {
			Thread.yield();
			long t = System.currentTimeMillis();
			while(System.currentTimeMillis() - t < 100) {
				
			}
			System.out.println("#" + getId() + " : do something...");
			
			
		}

		System.out.println("#" + getId() + " : Ended");
	}


	public static void main(String[] args) throws InterruptedException {

		System.out.println("采用 CachedThreadPool \n");
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i = 0; i < 5; i++) {
			exec.execute(new Exercise3());
		}
		exec.shutdown(); // 在所有任务结束后关闭线程池
		TimeUnit.SECONDS.sleep(2);


		System.out.println("\n 采用 FixedThreadPool(10) \n");
		exec = Executors.newFixedThreadPool(10);  // 如果分配的线程大于实际需要的线程, 一般只会用到实际需要的线程数
		for(int i = 0; i < 5; i++) {
			exec.execute(new Exercise3());
		}
		exec.shutdown();
		TimeUnit.SECONDS.sleep(2);


		System.out.println("\n 采用 FixedThreadPool(3) \n");
		exec = Executors.newFixedThreadPool(3);  // 如果分配的线程大于实际需要的线程, 一般只会用到实际需要的线程数
		for(int i = 0; i < 5; i++) {
			exec.execute(new Exercise3());
		}
		exec.shutdown();
		TimeUnit.SECONDS.sleep(2);
		
		
		System.out.println("\n 采用 SingleThreadPool \n");
		exec = Executors.newSingleThreadExecutor(); // 注意不是Pool
		for(int i = 0; i < 3; i++) {
			exec.execute(new Exercise3());
		}
		exec.shutdown();

	}
}
