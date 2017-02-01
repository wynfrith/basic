package containers.util;

// a generator interface

public interface Generator<T> {

	T next();  // return the last element
}