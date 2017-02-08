package threads.exercises;

/**
 * p669
 * 通过方法来创建线程
 */

import java.util.concurrent.*;
import threads.util.Generator;


public class Exercise10 {
	ExecutorService exec = Executors.newCachedThreadPool();

	public Future<Integer> runTask(int number) {
		return exec.submit(new Fibonacci(number));
	}

	private class Fibonacci implements Callable<Integer> {
		private int number;

		public Fibonacci(int n) {
			this.number = n;
		}

		private int fib(int n) {
			if(n < 2) return 1;
			else return fib(n - 1) + fib(n - 2);
		}

		private int sum() {
			int sum = 0;
			for(int i = 0; i < number; i++) {
				sum += fib(i);
			}
			return sum;
		}

		@Override
		public Integer call() {
			return sum();
		}

	}

	public static void main(String[] args) {
		Future<Integer> f = new Exercise10().runTask(3);
		try {
			System.out.println(f.get());
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
