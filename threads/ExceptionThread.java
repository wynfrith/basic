package threads;

import java.util.concurrent.*;

public class ExceptionThread {
	// 阻塞并格式化打印
	public static void print(String s) {
		try {
			Thread.sleep(100);
			System.out.println("\n\n" + s);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {

		System.out.println("未指定异常处理器情况下子线程的异常并没有出入给调用它的线程,而是直接打印到了控制台");
		try {
			new Thread(new UncaughtExceptionThread()).start();
		} catch(Exception e) {
			System.out.println("catch a exception");
		}

		print("给线程设置异常处理器");
		Thread t = new Thread(new UncaughtExceptionThread());

		t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			public void uncaughtException(Thread thread, Throwable e) {
				System.out.println("我异常处理器被捕获了->" + e);
			}
		});
		t.start();
		System.out.println("Handler::" + t.getUncaughtExceptionHandler());

		print("为多个线程设置异常处理器");
		ExecutorService pool = Executors.newCachedThreadPool(new MyThreadFactory());
		for(int i = 0; i < 3; i++) {
			pool.execute(new UncaughtExceptionThread());
		}
		pool.shutdown();


	}
}

class UncaughtExceptionThread implements Runnable {
	public void run() {
		throw new RuntimeException();
	}
}

class MyThreadFactory implements ThreadFactory {
	public Thread newThread(Runnable r) {
		Thread thread = new Thread(r);
		thread.setUncaughtExceptionHandler((t, e) -> {
			System.out.println("被处理器捕获.. " + e);
		});
		return thread;
	}
}















