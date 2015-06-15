package model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Attribute implements IData<String> {
	private Map<String, String> _values;

	public Attribute() {
		_values = new TreeMap(String.CASE_INSENSITIVE_ORDER);
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
	public boolean isEmpty() {
		return _values.size() == 0;
	}

	@Override
	public boolean isSimple(String key) {
		return true;
	}

	@Override
	public String get(final String key) {
		String result = _values.get(key);
		//String result = Config.prepareValue(_values.get(key));

		return result;
	}

	public Collection<String> getKeys() {
		return _values.keySet();
	}
}
