package threads.exercises;

/**
 * Page 656
 */

public class Exercise1 implements Runnable {
	private static int index;

	private int id = index++;

	public Exercise1() {
		System.out.println("#" + id + " : Task Init!");
	}

	@Override
	public void run() {
		for(int i = 0; i < 3; i++) {
			System.out.println("#" + id + ": Running");
			Thread.yield();
		}
		System.out.println("#" + id + ": Task Finished!");
	}

	public static void main(String[] args) {
		for(int i = 0; i < 4; i++) {
			new Thread(new Exercise1()).start();
		}
	}


} 