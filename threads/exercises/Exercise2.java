package threads.exercises;

import threads.util.Generator;
import java.util.Random;
import java.util.Arrays;

/**
 * Page 656
 * 用线程创建多个任务, 打印 fibonacci 数组
 */

class Fibonacci implements Generator<Integer>, Runnable {
	private static int taskCount;
	private final int id = taskCount++;

	private int count;
	private int size;

	public Fibonacci(int size) {
		this.size = size;
	}

	public int fib(int n) {
		if(n < 2) return 1;
		else return fib(n-2) + fib(n-1);
	}

	@Override
	public Integer next() {
		return fib(count++);
	}

	@Override
	public void run() {
		Integer[] fibs = new Integer[size];
		for(int i = 0; i < size; i++) {
			fibs[i] = next();
		}
		System.out.println("task " + id + " : " + Arrays.toString(fibs));
	}
}

public class Exercise2 {
	public static void main(String[] args) {
		Random r = new Random();
		for(int i = 0; i < 5; i++) {
			new Thread(new Fibonacci(r.nextInt(20)+ 1)).start();
		}	
	}
}



