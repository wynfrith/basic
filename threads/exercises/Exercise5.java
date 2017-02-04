// Callable, 创建有返回值的任务

package threads.exercises;

import java.util.concurrent.*;
import java.util.*;
import threads.util.Generator;

class Fibonacci implements Generator<Integer>, Callable<Integer> {

	private int index;
	private int size;

	public Fibonacci(int size) {
		this.size = size;
	}

	public int fib(int n) {
		return n < 2 ? 1 : fib(n-2) + fib(n-1);
	}

	@Override
	public Integer next() {
		return fib(index++);
	}

	@Override
	public Integer call() {
		System.out.println(Thread.currentThread() + " : " + "called");
		int sum = 0;
		for(int i = 0; i < size; i++) {
			sum += next();
		}
		return sum;
	}
}

public class Exercise5 {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();

		ArrayList<Future<Integer>> list = new ArrayList<>();
		for(int i = 41; i > 36; i--) {
			Future<Integer> f = exec.submit(new Fibonacci(i)); // 返回一个包装类, 可以使用get方法得到其值
			list.add(f);
			
		}
		exec.shutdown();

		// for(Future<Integer> f : list) {
		// 	System.out.println(f + " " + f.isDone() + "  size: " + list.size());
		// }

		
		while(list.size() > 0) { // 扫描列表中已完成的任务并将其移除
			for(Iterator<Future<Integer>> iter = list.iterator(); iter.hasNext();) {
				Future<Integer> f = iter.next();
				// System.out.println(f + " " + f.isDone() + "  size: " + list.size());
				if(f.isDone()) {
					try {
						System.out.println(f.get());  // 若任务没有完成, 那么g.get()方法将会阻塞, 知道任务完成为止
					} catch(Exception e) {
						e.printStackTrace();
					}
					
					iter.remove();
				}
			}
			Thread.yield();
		}

	
	}
}







