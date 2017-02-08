package threads;

import java.util.concurrent.*;

class Sleeper extends Thread {
	private int duration;
	public Sleeper(String name, int sleepTime) {
		super(name);
		duration = sleepTime;
		start();
	}

	public void run() {
		try {
			sleep(duration);
		} catch(InterruptedException e) { // !!! 捕获异常时会清理中断状态
			System.out.println(getName() + " isInterrupted : " + isInterrupted());  // false
			return;
		}
		System.out.println(getName() + " has awakened");
	}
}

class Joiner extends Thread {
	private Sleeper sleeper;
	public Joiner(String name, Sleeper s) {
		super(name);
		sleeper = s;
		start();
	}

	public void run() {
		try {
			sleeper.join();  // 如果调用join方法, 则等到这个线程的isAlive()为false时恢复执行
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(getName() + " join completed");
	}
}

public class Joining {
	public static void main(String[] args) throws InterruptedException{
		Sleeper
			s1 = new Sleeper("s1", 1500),
			s2 = new Sleeper("s2", 2000);
		Joiner
			j1 = new Joiner("j1", s1),
			j2 = new Joiner("j2", s2);

		s2.interrupt();

	}
}










