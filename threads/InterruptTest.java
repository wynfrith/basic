package threads;


public class InterruptTest extends Thread {
	public void run() {
		while(true) {
			System.out.println("isInterrupted? : " + Thread.currentThread().isInterrupted());
			if(Thread.interrupted()) {  // 该方法如果检测到已中断, 会将中断状态置回非中断状态
				System.out.println("Someone interrupted me");
			} else {
				System.out.println("going...");
			}

			long now = System.currentTimeMillis();
			while(System.currentTimeMillis() - now < 1000) {
				// .. 这个方法执行一秒
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread t = new InterruptTest();
		t.start();
		Thread.sleep(3000);
		t.interrupt();
	}

}

