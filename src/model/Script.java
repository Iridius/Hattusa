package model;

import controller.Utils;
import controller.XmlParser;

import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Logger;

public class Script implements IData<Attribute>, Cloneable {
	private final static String _PATH = "sys:path";
	private final static String _FROM = "sys:from";
	private final static String _TO = "sys:to";
	private final static String _PARENT = "{parent:name}";
	private final static String _CURRENT = "{current:name}";
	private final static String _INDEX = "{current:i}";
	private final static Logger log = Logger.getLogger(Script.class.getName());

	private String _path;
	private Map<String, Attribute> _attributes;
	private Collection<Script> _subscripts;

	//TODO: создать скрипт по имени файла
	public Script() {
		_path = "";
		_attributes = new LinkedHashMap();
		_subscripts = new LinkedList();
	}

	@Override
	public void put(String name, Attribute value) {
		_attributes.put(name, value);
	}

	@Override
	public int size() {
		return _attributes.size();
	}

	public Collection<Attribute> getAttributes(){
		return _attributes.values();
	}

	@Override
	public Attribute get(String key) {
		for(String k: _attributes.keySet()){
			if(k.equalsIgnoreCase(key)){
				return _attributes.get(k);
			}
		}

		return new Attribute("");
	}

	@Override
	protected Script clone() {
		try {
			return (Script) super.clone();
		} catch (CloneNotSupportedException e) {
			log.severe(e.getMessage());
		}

		return new Script();
	}

	@Override
	public String toString(){
		String result = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
		result += "\n<attributes>";

		for(String key: _attributes.keySet()) {
			Attribute attributes = _attributes.get(key);

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

		result += "\n" + "</attributes>";
		return result;
	}

	public Script run(String text){
		Script result = runScript(text);
		runChildScripts(result, text);

		return result;
	}

	private void runChildScripts(Script parent, String text) {
		for(Attribute attribute: this.getAttributes()) {
			if (attribute.isSystem()) {
				continue;
			}

			if (!attribute.isSimple()) {
				final String scope = Utils.getPattern(text, attribute.get(_FROM), attribute.get(_TO));
				final Script blank = XmlParser.getScript(Paths.get(Config.prepareValue(attribute.get("value"))));

				int child_number = 0;
				for(String pattern: getPatterns(blank, scope)){
					//TODO: объединить run и prepare
					//TODO: разобраться с двойным клонированием
					//TODO: передавать бланк в качестве параметра
					Script output = blank.clone().run(pattern).prepare(parent, child_number);
					parent._subscripts.add(output);

					child_number++;
				}
			}
		}
	}

	private Script runScript(final String text){
		Script result = new Script();

		for(Attribute attribute: this.getAttributes()) {
			Attribute newAttribute = attribute.clone();

			if (newAttribute.isSystem() && !newAttribute.getName().equalsIgnoreCase(_PATH)) {
				continue;
			}

			newAttribute.prepare(text);
			result.put(newAttribute.getName(), newAttribute);
		}

		return result;
	}

	private Collection<String> getPatterns(final Script script, final String text){
		final String from = script.get(_FROM).get("value");
		final String to = script.get(_TO).get("value");

		return Utils.getPatterns(text, from, to);
	}

	//TODO: игнорировать регистр символов
	private Script prepare(Script parent, int index) {
		Script result = this.clone();

		for(Attribute attribute: this.getAttributes()){
			String value = attribute.get("value");

			if(value.contains(_PARENT)){
				value = value.replace(_PARENT, parent.get("name").get("value"));
			}
			if(value.contains(_CURRENT)){
				value = value.replace(_CURRENT, this.get("name").get("value"));
			}
			if(value.contains(_INDEX)){
				value = value.replace(_INDEX, Integer.toString(index));
			}

			attribute.put("value", value);

			//TODO: весьма странная конструкция. Разобраться, надо так делать или нет
			result.put(attribute.getName(), attribute);
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
