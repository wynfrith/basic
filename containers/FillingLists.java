package containers;

// The Collections.fill() & Collections.nCopies() methods

import java.util.*;

class StringAddress {
	private String s;

	public StringAddress(String s) { this.s = s; }

	@Override
	public String toString() {
		return super.toString() + " " + s;
	}
}


public class FillingLists {

	public static void main(String [] args) {

		/**
		 * @param <T> the class of the object to copy and of the objects in the returned list
		 * @param number the number of elements in the returned list
		 * @param object the elements to appear repeatedly in the returned list
		 * @return an immutable(不可变的) list consisting of n copies of the specific object
		 */
		List<StringAddress> immutableList = Collections.nCopies(4, new StringAddress("Hello"));
		List<StringAddress> list = new ArrayList<>(immutableList);

		System.out.println(list);

		/**
		 * @param list the list to be filled with the specified element
		 * @param obj the element with which to fill the specified list
		 */
		Collections.fill(list, new StringAddress("world"));

		System.out.println(list);

	}
}