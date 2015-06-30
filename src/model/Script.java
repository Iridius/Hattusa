package model;

import controller.Utils;
import controller.XmlParser;

import java.nio.file.Paths;
import java.util.*;

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
		Script result = new Script();

		for(Attribute attribute: this.getAttributes()){
			if(attribute.isSystem()){
				continue;
			}

			if(!attribute.isSimple()){
				_subscripts = getChildren(attribute, Utils.getPattern(text, attribute.get(_FROM), attribute.get(_TO)));
			}

			//TODO: не перезаписывать атрибут, чтобы не было жесткого порядка вычисления значения/получения дочерных аттрибутов
			attribute.prepare(text);
			result.put(attribute.getName(), attribute);
		}

		return result.toString();
	}

	private Collection<Script> getChildren(final Attribute parent, final String text) {
		Collection<Script> result = new LinkedList();

		final Script blank = XmlParser.getScript(Paths.get(Config.prepareValue(parent.get("value"))));
		final String from = blank.get(_FROM).get("value");
		final String to = blank.get(_TO).get("value");

		int currentIndex = 0;
		for(String pattern: Utils.getPatterns(text, from, to)){
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

	public Collection<Script> getScripts() {
		return _subscripts;
	}

	public Script getScript(int i) {
		return (Script)_subscripts.toArray()[i];
	}
}
