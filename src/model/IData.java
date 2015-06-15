package model;

import java.util.Collection;

public interface IData<T> {
	Collection<String> getKeys();
	String get(String key);
	void put(String name, T value);

	int size();
	boolean isEmpty();
	boolean isSimple(String key);
}
