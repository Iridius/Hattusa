package model;

public interface IData<T> {
	String get(String key);
	void put(String name, T value);

	int size();
	boolean isEmpty();
}
