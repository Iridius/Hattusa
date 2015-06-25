package model;

import Alexandria.Library;
import controller.XmlParser;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	//TODO: переименовать в prepare после удаления закомментированного текста
	public void prepareValue(final String text) {
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
			final int from = text.indexOf(str_from) + str_from.length();
			final int to = text.indexOf(_values.get("sys:to").replace("<![CDATA[", "").replace("]]>", ""), from);

			value = text.substring(from, to);
		}
		if(this.isSimple()) {
			return value;
		}

		return getChildPathValue();
	}

	private String getChildPathValue() {
		Path blank = Paths.get(Config.prepareValue(_values.get("value")));
		Script script = XmlParser.getScript(blank);

		final String path = script.get("sys:path").get("value");
		final String folder = path.substring(0, path.lastIndexOf("\\") + 1);

		return folder;
	}

	public FileData getChildScripts(final Script parent, final String text) {
		FileData result = new FileData();
		//String from = this.get("sys:from");
		//String to = this.get("sys:to");

		//Pattern pattern = Pattern.compile(from + "(.*)" + to, Pattern.DOTALL);
		//Matcher matcher = pattern.matcher(text);

		//Collection<String> insertions = new LinkedList();
		//while(matcher.find()){
		//	insertions.add(matcher.group());
		//}

		//int from = text.indexOf(this.get("sys:from"));
		//int to = text.indexOf(this.get("sys:to"), from);

//		Path path = Paths.get(Config.prepareValue(this.get("value")));
//		XmlParser parser = new XmlParser(path);
//		Script template = parser.getScript();
//
//		for(int i = 0; i < getChildScriptCount(text, template); i++){
//			final String parentName = parent.get("name").get("value");
//			final String childPath = template.get("sys:path").get("value");
//			childPath = childPath.replace("current:i", Integer.toString(i));
//			childPath = childPath.replace("parent:name", parentName);
//
//			Script child = template;
//			child.get("sys:path")._values
//			String childText = "";
//			result.put(Decompiler.run(childText, template));
//		}

		return result;
	}

	private static int getChildScriptCount(final String text, final Script script) {
		Attribute requiredAttribute = null;
		for(Attribute attribute: script.getAttributes()){
			if(attribute.getKeys().contains("sys:required")){
				requiredAttribute = attribute;
			}
		}
		if(requiredAttribute == null){
			return 0;
		}

		Pattern pattern = Pattern.compile(requiredAttribute.get("sys:from") + "(.*)" + requiredAttribute.get("sys:to"));
		Matcher matcher = pattern.matcher(text);

		int matches = 0;
		while(matcher.find()){
			matches++;
		}

		return matches;
	}
}
