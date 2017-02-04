package threads.util;

public interface Generator<T> {
	T next(); // return the last element.
}