package containers;

// 测试MapData
// 创建了一个可以返回字母的Letters Generator

import java.util.*;
import containers.util.*;

class Letters implements Generator<Pair<Integer, String>>, Iterable<Integer> {
	private int number = 1;
	private char letter = 'A';
	private int size = 10;

	public Pair<Integer, String> next() {
		return new Pair<>(number++, "" + letter++);
	}

	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {
			public Integer next() { return number++; }
			public boolean hasNext() { return number < size; }
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	
}

public class MapDataTest {
	public static void main(String[] args) {
		Map<Integer, String> map = new LinkedHashMap<>(
			MapData.map(new Letters(), 15)
			);
		System.out.println(map);

		map = new LinkedHashMap<>(
				MapData.map(new Letters(), "Wynfrith")
			);
		System.out.println(map);
	}
}