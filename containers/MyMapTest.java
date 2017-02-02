package containers;

// 自己的Map实现
// 使用享元设计模式
// TODO: 搞明白静态内部类!!

import java.util.*;
public class MyMapTest {  

	public static final String[][] DATA = {
		{"wynfrith", "zibo"},
		{"yangcao", "zibo"},
		{"yazhou", "zaozhuang"},
		{"zeyang", "qingdao"},
		{"maxiaoheng", "liaocheng"},
		{"zhouyong", "weifang"}
	};

	public static void main(String[] args) {
		for(Map.Entry<String, String> entry : new MyMap().entrySet()) {
			System.out.println(entry.getKey() + " " +entry.getValue());
		}

	}

	// 静态内部类不能包含在不同内部类之中
	static class MyMap extends AbstractMap<String, String> {

		private static Set<Map.Entry<String,String>> entries = new EntrySet(DATA.length);

		public Set<Map.Entry<String,String>> entrySet() { // 返回一个包含entry实体的集(视图)
			return entries; 
			// return new EntrySet(DATA.length);  不要这样做, 这样每次调用entrySet都创建一份新的Set对象
		}

		// 声明的是嵌套类, 与其外围类没有联系, 即嵌套类不包含外围类的引用
		// 普通内部类不能再内部声明静态的变量和方法, 而嵌套类可以
		private static class Entry implements Map.Entry<String, String> {
			public Entry(int index) { this.index = index; }

			int index;

			public boolean equals(Object o) {
				return DATA[index][0].equals(o);  // 在这里, object与key相同视为等同
			}
			public int hashCode() {
				return Objects.hash(DATA[index][0]);
			}
			public String getKey() {
				return DATA[index][0];
			}
			public String getValue() {
				return DATA[index][1];
			}
			public String setValue(String v) {
				throw new UnsupportedOperationException();
			}

		}

		private static class EntrySet extends AbstractSet<Map.Entry<String, String>> {
			public EntrySet(int size) {
				if(size < 0) this.size = 0;
				else if(size > DATA.length) this.size = DATA.length;
				else this.size = size;
			}
			private int size;
			public int size() {
				return size;
			}

			public Iterator<Map.Entry<String, String>> iterator() {
				return new Iterator<Map.Entry<String, String>>() {  // 注意: <> 无法与匿名内部类一起使用
					Entry entry = new Entry(-1);
					public Map.Entry<String, String> next() {
						++entry.index;
						return entry;
					};
					public boolean hasNext() {
						return entry.index < size - 1;
					}
				};
			}
		}


	}

}