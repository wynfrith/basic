package containers;

import java.util.*;
import containers.util.*;

class WordGenerator implements Generator<String> {
	private int index;

	String[] words = ("hello every one my name is wynfrith").split(" ");

	public String next() {
		return words[index++];
	}
}

public class CollectionDataTest {
	public static void main(String[] args) {
		Set<String> set = new LinkedHashSet<>(
			CollectionData.list(new WordGenerator(), 5));
		System.out.println(set);

		set.addAll(new CollectionData<String>(new WordGenerator(), 6));
		System.out.println(set);
	}	
}