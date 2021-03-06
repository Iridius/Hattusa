package model;

public interface IData<T> {
	T get(String key);
	void put(String name, T value);

	int size();
}
