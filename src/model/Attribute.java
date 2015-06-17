package model;

import Alexandria.Library;

import java.util.*;

public class Attribute implements IData<String> {
	private String _name;
	private LinkedHashMap<String,String> _values;

	public Attribute(final String name) {
		_name = name;
		_values = new LinkedHashMap();
	}

	@Override
	public void put(final String name, final String value) {
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
		String value = _values.get("value");
		if(value == null || value.equals("")){
			return true;
		}
		if(value.endsWith(".template")){
			return true;
		}

		return !Library.isPath(Config.prepareValue(value));
	}

	public String getValue(final String text) {
		String from = get("sys:from");
		if(from.length() == 0){
			return get("value");
		}

		String to = get("sys:to");
		if(to.length() == 0){

		}

		return "";
	}
}
