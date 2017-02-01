package containers.util;

// A Collection filled with data using a generator object
// 集合生成器 - 通过生成器生成一个集合

import java.util.*;

public class CollectionData<T> extends ArrayList<T> {

	public CollectionData(Generator<T> gen, int quantity) {
		for(int i = 0; i < quantity; i++) {
			add(gen.next());
		}
	}

	// A generic convenience method : 调用方法会自动推断类型, 从而省略泛型符号
	public static <T> CollectionData<T> list(Generator<T> gen, int quantity) {
		return new CollectionData<T>(gen, quantity);
	}

}