package threads;

import java.util.concurrent.*;

// 创建一个内部类, 并且在构造器中创建这个内部类实例
class InnerThread1 {

	private int countDown = 5;
	private Inner inner;

	public InnerThread1(String name) {
		inner = new Inner(name);
	}

	private class Inner extends Thread {
		public Inner(String name) {
			super(name);
			start(); // 构造后立即启动
		}
		@Override
		public String toString() {
			return getName() + "  " + countDown;
		}
		@Override
		public void run() {
			try {
				while(countDown-- > 0) {
					System.out.println(this);
					sleep(10);
				}
			} catch(InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}

// 创造匿名内部类
class  InnerThread2 {
	private int countDown = 5;

	public InnerThread2(String name) {
		Thread t = new Thread(name) {
			@Override
			public String toString() {
				return getName() + "  " + countDown;
			}
			@Override
			public void run() {
				try {
					while(countDown-- > 0) {
						System.out.println(this);
						sleep(10);
					}					
				} catch(InterruptedException e) {
					e.printStackTrace();
				}

			}
		};
		t.start();
	}
}


// 通过Runnable方式
class InnerRunnable1 {
	private int countDown = 5;
	private Inner inner;

	public InnerRunnable1(String name) {
		inner = new Inner(name);
	}

	private class Inner implements Runnable {
		Thread t;
		public Inner(String name) {
			t = new Thread(this, name);
			t.start();
		}

		@Override
		public String toString() {
			return t.getName() + " " + countDown;
		}

		@Override
		public void run() {
			while(countDown-- > 0) {
				try {
					System.out.println(this);
					TimeUnit.MILLISECONDS.sleep(10);
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}
}


class InnerRunnable2 {
	private int countDown = 5;
	Thread t;

	public InnerRunnable2(String name) {
		t = new Thread(new Runnable() {
			@Override
			public String toString() {
				return t.getName() + "  " + countDown;
			}
			@Override
			public void run() {
				while(countDown-- > 0) {
					try {
						System.out.println(this);
						TimeUnit.MILLISECONDS.sleep(10);
					} catch(InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, name);

		t.start();
	}

}

class ThreadMethod {
	private int countDown = 5;
	private Thread t;
	private String threadName;
	public ThreadMethod(String threadName) { this.threadName = threadName; }

	public void runTask() {
		if(t == null) {
			t = new Thread(threadName) {
				@Override
				public void run() {
					try {
						while(countDown-- > 0) {
							System.out.println(this);
							sleep(10);
						}
					} catch(InterruptedException e) {
						e.printStackTrace();
					}
				}
				@Override
				public String toString() {
					return getName() + "  " + countDown;
				}
			};
			t.start();
		}
	}
}

public class ThreadVariations {
	public static void main(String[] args) {
		new InnerThread1("InnerThread1");
		new InnerThread2("InnerThread2");

		new InnerRunnable1("InnerRunnable1");
		new InnerRunnable2("InnerRunnable2");

		new ThreadMethod("ThreadMethod").runTask();
	}
}











