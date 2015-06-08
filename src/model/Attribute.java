package model;

import java.util.Collection;
import java.util.HashMap;

public class Attribute implements IData<String> {
	private HashMap<String, String> _values;

	public Attribute() {
		_values = new HashMap();
	}

	@Override
	public void put(String name, String value) {
		_values.put(name.toLowerCase(), value.toLowerCase());
	}

	@Override
	public int size() {
		return _values.size();
	}

	@Override
	public boolean isEmpty() {
		return _values.size() == 0;
	}

	@Override
	public String get(final String key) {
		String result = Config.prepareValue(_values.get(key));

		return result;
	}

	public Collection<String> getKeys() {
		return _values.keySet();
	}
}
