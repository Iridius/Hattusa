package controller;

import model.Attribute;
import model.Config;
import model.Script;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class Decompiler {
	private final static Logger log = Logger.getLogger(Decompiler.class.getName());
	private final String _text;
	private final Script _script;
	private String valuePath;


	public Decompiler(String text, Script script) {
		_text = text;
		_script = script;
	}

	public String run() {
		String result = "<?xml version=\"1.0\" encoding=\"windows-1251\"?>";
		result += "\n<attributes>";

		for(String key: _script.getKeys()){
			if(_script.isSystem(key)){
				continue;
			}

			Attribute attribute = _script.get(key);

			if(attribute.isSimple()){
				result += "\n\t<" + key + ">" + attribute.get("value") + "</" + key + ">";
			}
			else{
				result += "\n\t<" + key + ">";
				result += getComplexValue(attribute);
				result += "\n\t</" + key + ">";
				//runChild(attribute);
			}
		}

		result += "\n</attributes>";
		return result;
	}

//	private void runChild(String attribute) {
//		final String text = getText(attribute);
//		if(text.length() == 0){
//			return "";
//		}
//	}

	private String getComplexValue(Attribute attribute) {
		String result = "";
		for(String key: attribute.getKeys()){
			if(attribute.isSystem(key)){
				continue;
			}

			String value = attribute.get(key);
			if(key.equalsIgnoreCase("value")){
				value = getValuePath(value);
			}

			result += "\n\t\t<" + key + ">" + value + "</" + key + ">";
		}

		return result;
	}

//	private String getText(String attribute) {
//		final String from = _script.get(attribute + ".from");
//		final String to = _script.get(attribute + ".to");
//
//		if(from.length() == 0){
//			return "";
//		}
//
//		final int pos_from = _text.indexOf(from) + from.length();
//		final int pos_to = to.length() != 0 ? _text.indexOf(to, pos_from) : _text.length();
//
//		return _text.substring(pos_from, pos_to);
//	}

//	private String getSimpleValue(final String key){
//		if(key.equalsIgnoreCase("path")){
//			return "";
//		}
//
//		return "\n\t<" + key + ">" + _script.get(key).get("value") + "</" + key + ">";
//	}

	public String getValuePath(final String value) {
		Path valuePath = Paths.get(Config.prepareValue(value));
		XmlParser parser = new XmlParser(valuePath);

		return parser.getScript().get("sys:path").get("value");
	}
}
