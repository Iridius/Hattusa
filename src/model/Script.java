package model;

import controller.XmlParser;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Script implements IData<Attribute> {
	private final static String _PATH = "sys:path";

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

	@Override
	public Collection<String> getKeys() {
		return _script.keySet();
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
				//text = cutText(text, attribute.get("sys:from"), attribute.get("sys:to"));
				//_subscripts = getChildren(attribute, text);
				_subscripts = getChildren(attribute, cutText(text, attribute.get("sys:from"), attribute.get("sys:to")));
			}

			//TODO: не перезаписывать атрибут, чтобы не было жесткого порядка вычисления значения/получения дочерных аттрибутов
			attribute.prepareValue(text);
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

	private Collection<Script> getChildren(final Attribute attribute, final String text) {
		Collection<Script> result = new LinkedList();

		Path path = Paths.get(Config.prepareValue(attribute.get("value")));
		Script blank = XmlParser.getScript(path);


		return result;
	}

	public Collection<Script> getScripts() {
		return _subscripts;
	}

	public Script getScript(int i) {
		return (Script)_subscripts.toArray()[i];
	}
}
