package threads.exercises;

import threads.util.Generator;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class Exercise4 {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i = 1; i < 6; i++) {
			exec.execute(new Fibonacci(i));
		}
		exec.shutdown(); // shutdown后 ExecutorService对象会拒绝新的任务,但会做完当前正在执行的任务
		TimeUnit.SECONDS.sleep(2);
		System.out.println();


		exec = Executors.newFixedThreadPool(3);
		for(int i = 0; i < 5; i++) {
			exec.execute(new Fibonacci(i));
		}
		exec.shutdown();
		TimeUnit.SECONDS.sleep(2);
		System.out.println();

		exec = Executors.newSingleThreadExecutor();
		for(int i = 35; i < 37; i++) {
			exec.execute(new Fibonacci(i));
		}
		exec.shutdown();
		System.out.println();

	}

}


class Fibonacci implements Generator<Integer>, Runnable {
	private int n;
	private int size;

	public Fibonacci(int size) {
		this.size = size;
	}

	public int fib(int n) {
		if(n < 2) return 1;
		else return fib(n-1) + fib(n-2);
	}

	@Override
	public Integer next() {
		return fib(n++);
	}

	@Override
	public void run() {
		// print fib arrays
		int[] fibs = new int[size];
		for(int i = 0; i < size; i++) {
			fibs[i] = next();
		}
		System.out.println(Thread.currentThread() + " : " + Arrays.toString(fibs));
	}
}