package threads;

public class LiffOff implements Runnable {

	private int countDown = 10;
	private static int taskCount = 0;
	private int id = taskCount++;

	public LiffOff() {}
	public LiffOff(int count) {
		this.countDown = count;
	}

	public String toString() {
		return "#" + id + " (" + 
			(countDown > 0 ? countDown : "LiftOff") + ")";
	}

	public void run() {
		while(countDown-- > 0) {
			System.out.println(this);
			Thread.yield();
		}
	}

}