package containers.util;

// 存储一个键值对

public class Pair<K, V> {
	public K key;
	public V value;
	public Pair(K k, V v) {
		key = k;
		value = v;
	}

}