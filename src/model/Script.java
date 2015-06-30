package model;

import controller.XmlParser;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Script implements IData<Attribute> {
	private final static String _FROM = "sys:from";
	private final static String _TO = "sys:to";
	private final static String _PARENT = "{parent:name}";
	private final static String _CURRENT = "{current:name}";
	private final static String _INDEX = "{current:i}";

	private String _path;
	private Map<String, Attribute> _script;
	private Collection<Script> _subscripts;

	//TODO: создать скрипт по имени файла
	public Script() {
		_path = "";
		_script = new LinkedHashMap();
		_subscripts = new LinkedList();
	}

	@Override
	public void put(String name, Attribute value) {
		_script.put(name, value);
	}

	@Override
	public int size() {
		return _script.size();
	}

	public Collection<Attribute> getAttributes(){
		return _script.values();
	}

	@Override
	public Attribute get(String key) {
		for(String k: _script.keySet()){
			if(k.equalsIgnoreCase(key)){
				return _script.get(k);
			}
		}

		return new Attribute("");
	}

	@Override
	public String toString(){
		String result = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
		result += "\n<attributes>";

		for(String key: _script.keySet()) {
			Attribute attributes = _script.get(key);

			if(attributes.size() == 1 && attributes.getKeys().contains("value")){
				result += "\n\t<" + key + ">" + attributes.get("value") + "</" + key + ">";
				continue;
			}

			result += "\n\t<" + key + ">";
			for(String attributeKey: attributes.getKeys()){
				String value = attributes.get(attributeKey);
				if(value.contains("<") || value.contains(">")){
					value = "<![CDATA[" + value + "]]>";
				}

				result += "\n\t\t<" + attributeKey + ">" + value + "</" + attributeKey + ">";
			}
			result += "\n\t</" + key + ">";
		}

		result += "\n" +
				"</attributes>";

		return result;
	}

	public String run(String text) {
		Script outputScript = new Script();
		for(Attribute attribute: this.getAttributes()){
			if(attribute.isSystem()){
				continue;
			}

			if(!attribute.isSimple()){
				_subscripts = getChildren(attribute, cutText(text, attribute.get("sys:from"), attribute.get("sys:to")));
			}

			//TODO: не перезаписывать атрибут, чтобы не было жесткого порядка вычисления значения/получения дочерных аттрибутов
			attribute.prepare(text);
			outputScript.put(attribute.getName(), attribute);
		}

		return outputScript.toString();
	}

	private static String cutText(final String text, final String from, final String to) {
		int start = text.indexOf(from) + from.length();
		if(start == -1){
			return "";
		}

		int end = text.indexOf(to, start);
		if(end == -1){
			end = text.length() - start;
		}

		return text.substring(start, end);
	}

	private Collection<Script> getChildren(final Attribute parent, final String text) {
		Collection<Script> result = new LinkedList();

		final Path path = Paths.get(Config.prepareValue(parent.get("value")));
		final Script blank = XmlParser.getScript(path);

		int currentIndex = 0;
		for(String pattern: getPatterns(text, blank)){
			Script output = new Script();

			for(Attribute blank_attribute: blank.getAttributes()){
				Attribute attribute = blank_attribute.clone();
				attribute.prepare(pattern);

				//TODO: разобраться в отличии метода Script.curText от Attribute.extractValue
				if(!attribute.isSystem()) {
					output.put(attribute.getName(), attribute);
				}
			}

			output.prepare(this, currentIndex);
			currentIndex++;

			result.add(output);
		}


		return result;
	}

	private void prepare(Script parent, int index) {
		for(Attribute attribute: this.getAttributes()){
			String value = attribute.get("value");

			if(value.contains(_PARENT)){
				attribute.put("value", value.replace(_PARENT, parent.get("name").get("value")));
			}
			if(value.contains(_CURRENT)){
				attribute.put("value", value.replace(_CURRENT, this.get("name").get("value")));
			}
			if(value.contains(_INDEX)){
				attribute.put("value", value.replace(_INDEX, Integer.toString(index)));
			}
		}
	}

	private Collection<String> getPatterns(String text, Script script) {
		Collection<String> result = new LinkedList();

		String from = script.get(_FROM).get("value");
		String to = script.get(_TO).get("value");

		Pattern pattern = Pattern.compile(from + "(.*?)" + to, Pattern.DOTALL);
		Matcher matcher = pattern.matcher(text);

		while(matcher.find()){
			result.add(matcher.group());
		}

		return result;
	}

	public Collection<Script> getScripts() {
		return _subscripts;
	}

	public Script getScript(int i) {
		return (Script)_subscripts.toArray()[i];
	}
}
