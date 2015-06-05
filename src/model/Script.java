package model;

import java.util.HashMap;

public class Script implements IData<Attribute> {
	private HashMap<String, Attribute> _script;

	public Script() {
		_script = new HashMap<>();
	}

	@Override
	public void put(String name, Attribute value) {
		_script.put(name, value);
	}

	@Override
	public int size() {
		return _script.size();
	}

	@Override
	public boolean isEmpty() {
		return _script.size() == 0;
	}

	@Override
	public String get(String key) {
		if(key.indexOf(".") <= 0) {
			return _script.get(key).get("value").toString();
		}

		final String tag = key.substring(0, key.indexOf("."));
		final String attribute = key.substring(tag.length() + 1);

		return _script.get(tag).get(attribute).toString();
	}
}
