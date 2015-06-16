package model;

import java.util.*;

public class Attribute implements IData<String> {
	private LinkedHashMap<String,String> _values;

	public Attribute() {
		_values = new LinkedHashMap();
	}

	@Override
	public void put(String name, String value) {
		_values.put(name, value);
	}

	@Override
	public int size() {
		return _values.size();
	}

	@Override
	public boolean isSystem(String key) {
		return key.startsWith("sys:");
	}

	public boolean isEmpty() {
		return _values.size() == 0;
	}

	@Override
	public String get(final String key) {
		for(String k: _values.keySet()){
			if(k.equalsIgnoreCase(key)) {
				return _values.get(k);
			}
		}

		return "";
	}

	public Collection<String> getKeys() {
		return _values.keySet();
	}

	public boolean isSimple() {
		return size() == 1 && _values.containsKey("value");
	}
}
