package containers.util;

// Map生成器

import java.util.*;

public class MapData<K, V> extends LinkedHashMap<K, V> {

	// A single pair generator constructor
	public MapData(Generator<Pair<K, V>> gen, int quantity) {
		for(int i = 0; i < quantity; i++) {
			Pair<K,V> pair = gen.next();  // 注意: 不要忘了加泛型的类型
			put(pair.key, pair.value);
		}
	}

	// two separate generators constructor
	public MapData(Generator<K> genK, Generator<V> genV, int quantity) {
		for(int i = 0; i < quantity; i++) {
			put(genK.next(), genV.next());
		}
	}

	// A key generator and a single value constructor
	public MapData(Generator<K> genK, V value, int quantity) {
		for(int i = 0; i < quantity; i++) {
			put(genK.next(), value);
		}
	}

	// An Iterable(包括其子类Collection) and  a value Generator
	public MapData(Iterable<K> iter, Generator<V> genV) {
		for(K k : iter) {
			put(k, genV.next());
		}
	}

	public MapData(Iterable<K> iter, V value) {
		for(K k : iter) {
			put(k, value);
		}
	}

	// Generic covenience methods
	
	public static <K, V> MapData<K, V> map(Generator<Pair<K, V>> gen, int q) {
		return new MapData<>(gen, q);
	}

	public static <K, V> MapData<K, V> map(Generator<K> genK, Generator<V> genV, int q) {
		return new MapData<>(genK, genV, q);
	}

	public static <K, V> MapData<K, V> map(Generator<K> genK, V value, int q) {
		return new MapData<>(genK, value, q);
	}

	public static <K, V> MapData<K, V> map(Iterable<K> iter, Generator<V> genV) {
		return new MapData<>(iter, genV);
	}

	public static <K, V> MapData<K, V> map(Iterable<K> iter, V value) {
		return new MapData<>(iter, value);
	}

}