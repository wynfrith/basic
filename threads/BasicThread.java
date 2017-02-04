package threads;

public class BasicThread {

	public static void main(String[] args) throws InterruptedException 	{
		Thread t = new Thread(new LiftOff());
		t.start();

		Thread.sleep(2000);
		for(int i = 0; i < 5; i++) {
			new Thread(new LiftOff()).start();
		}
	}

}

class LiftOff implements Runnable {
	// 静态代码块中不能声明类变量, 只是用来初始化的, 又称为初始化块. static {  ...  }
	
	private static int index;

	private int count = 10;

	private final int id = index++;

	public String status() {
		return "#" + id + "("+ (count > 0 ? count : "LiftOff") + ")";  // 三目运算在拼接字符串时不要忘记加括号
	}

	@Override
	public void run() {
		while(count-- > 0) {
			System.out.print(status() + ", ");
			Thread.yield();
		}
	}
}