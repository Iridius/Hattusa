package model;

import java.util.Collection;

public interface IData<T> {
	Collection<String> getKeys();
	T get(String key);
	void put(String name, T value);

	int size();

	boolean isSystem(String key);
}
