package threads.test;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

class UnsafeThread implements Runnable{

	 List<Object> list;

	 public UnsafeThread(List<Object> l) {
	 	list = l;
	 }

	public void run() {
		for(int i = 0; i < 100; i++) {
			// System.out.println(Thread.currentThread() + "  " + i);
			list.add(new Object());
		}
		
	}

}

public class UnsafeThreadTest {

	
	public static void test(List<Object> list) {

		// create a pool
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i = 0; i < 100; i++) {
			exec.execute(new UnsafeThread(list));
		}
		exec.shutdown();

		// 等待子线程的结束
		try {
			exec.awaitTermination(10, TimeUnit.SECONDS);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(list.size());
	}


	public static void main(String[] args) {

		// 使用非线程安全的ArrayList类
		System.out.println("ArrayList:");
		for(int i = 0; i < 10; i++) {
			test(new ArrayList<>());
		}
		System.out.println("\nVector: ");
		// 使用线程安全的Vector类
		for(int i = 0; i < 10; i++) {
			test(new Vector<>());
		}
	}
}