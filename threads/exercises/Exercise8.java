package threads.exercises;

/**
 * p665
 * 验证main()退出, 程序立刻终止
 */

import java.util.concurrent.*;
import threads.LiffOff;

public class Exercise8 {
	public static void main(String[] args) {
		Thread t;
		for(int i = 0; i < 5; i++) {
			t = new Thread(new LiffOff());
			t.setDaemon(true);
			t.start();
		}
		System.out.println("ARE YOU OK?");
	}
}