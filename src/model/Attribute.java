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
	public String toString(){
		return _name;
	}

	@Override
	public void put(final String name, final String value) {
		_values.put(name, value);
	}

	@Override
	public int size() {
		return _values.size();
	}

	public boolean isSystem() {
		return _name.startsWith("sys:");
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
		if(_name.toLowerCase().contains("sys:")){
			return true;
		}

		return !Library.isPath(Config.prepareValue(value));
	}

//	public String getValue(final String text) {
//		String from = get("sys:from");
//		if(from.length() == 0){
//			return get("value");
//		}
//
//		String to = get("sys:to");
//		if(to.length() == 0){
//
//		}
//
//		return "";
//	}

	public String getName() {
		return _name;
	}

	public void prepareValue(final String text) {
		LinkedHashMap<String,String> outputValues = new LinkedHashMap();

		final String str_from = _values.get("sys:from").replace("<![CDATA[", "").replace("]]>", "");
		final int from = text.indexOf(str_from) + str_from.length();
		final int to = text.indexOf(_values.get("sys:to").replace("<![CDATA[", "").replace("]]>", ""), from);

		final String value = text.substring(from, to);
		outputValues.put("value", value);

		for(String tag: _values.keySet()){
			if(tag.startsWith("sys:")){
				continue;
			}
			outputValues.put(tag, _values.get(tag));
		}

		_values = outputValues;
	}

	public FileData getChildScripts() {
		return new FileData();
	}
}
