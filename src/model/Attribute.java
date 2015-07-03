package model;

import Alexandria.Library;
import controller.Utils;
import controller.XmlParser;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.logging.Logger;

public class Attribute implements IData<String>, Cloneable {
	private String _name;
	private LinkedHashMap<String,String> _values;
	private final static Logger log = Logger.getLogger(Attribute.class.getName());

	public Attribute(final String name) {
		_name = name;
		_values = new LinkedHashMap();
	}

	@Override
	public String toString(){
		String result = "";
		for(String key: _values.keySet()){
			result += " (" + key + "; " + _values.get(key) + "), ";
		}
		if(result.length() != 0){
			return _name + ": " + result. substring(0, result.length() - 1);
		}

		return _name;
	}

	@Override
	public void put(final String name, final String value) {
		//TODO: тест на замещение значения
		if(_values.containsKey(name)){
			_values.remove(name);
		}

		_values.put(name, value);
	}

	@Override
	public int size() {
		return _values.size();
	}

	public boolean isSystem() {
		return _name.startsWith("sys:");
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

	@Override
	protected Attribute clone() {
		try {
			return (Attribute) super.clone();
		} catch (CloneNotSupportedException e) {
			log.severe(e.getMessage());
		}

		return new Attribute("");
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

	public String getName() {
		return _name;
	}

	public void prepare(final String text) {
		LinkedHashMap<String,String> outputValues = new LinkedHashMap();

		for(String tag: _values.keySet()){
			if (tag.equalsIgnoreCase("value") || tag.equalsIgnoreCase("sys:from")) {
				outputValues.put("value", extractValue(text));
				continue;
			}

			if(tag.startsWith("sys:")){
				continue;
			}

			outputValues.put(tag, _values.get(tag));
		}

		_values = outputValues;
	}

	private String extractValue(final String text) {
		String value = _values.get("value");
		if(_values.containsKey("sys:from")) {
			final String str_from = _values.get("sys:from").replace("<![CDATA[", "").replace("]]>", "");
			final String str_to = _values.get("sys:to").replace("<![CDATA[", "").replace("]]>", "");

			//final int from = text.indexOf(str_from) + str_from.length();
			//final int to = text.indexOf(, from);
			//value = text.substring(from, to);

			value = Utils.getPattern(text, str_from, str_to);
		}
		if(this.isSimple()) {
			return value;
		}

		return getChildPathValue();
	}

	private String getChildPathValue() {
		Path blank = Paths.get(Config.prepareValue(_values.get("value")));
		Script script = XmlParser.getScript(blank);

		//TODO: тест на замену ссылок
		final String path = script.get("sys:path").get("value").replace("{parent:","{current:");
		final String folder = path.substring(0, path.lastIndexOf("\\") + 1);

		return folder;
	}
}
