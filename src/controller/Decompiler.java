package controller;

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

		for(String attribute: _script.getKeys()){
			if(_script.isSimple(attribute)){
				result += getSimpleValue(attribute);
			}
			else{
				result += "\n";
				result += getComplexValue(attribute);
			}
		}

		result += "\n</attributes>";
		return result;
	}

	private String getComplexValue(String attribute) {
		String result = "\n\t<" + attribute + ">";

		final String text = getText(attribute);
		if(text.length() == 0){
			return "";
		}

		String valuePath = getValuePath(attribute);
		if(valuePath.length() != 0){
			result += "\n\t\t<value>" + valuePath + "</value>";
		}




		result += "\n\t</" + attribute + ">";
		return result;
	}

	private String getText(String attribute) {
		final String from = _script.get(attribute + ".from");
		final String to = _script.get(attribute + ".to");

		if(from.length() == 0){
			return "";
		}

		final int pos_from = _text.indexOf(from) + from.length();
		final int pos_to = to.length() != 0 ? _text.indexOf(to, pos_from) : _text.length();

		return _text.substring(pos_from, pos_to);
	}

	private String getSimpleValue(final String attribute){
		if(attribute.equalsIgnoreCase("path")){
			return "";
		}

		return "\n\t<" + attribute + ">" + _script.get(attribute) + "</" + attribute + ">";
	}

	public String getValuePath(final String attribute) {
		String value = _script.get(attribute + ".value");
		if(value.length() == 0) {
			return "";
		}

		Path valuePath = Paths.get(Config.prepareValue(value));
		XmlParser parser = new XmlParser(valuePath);

		Script childScript = parser.getScript();
		String childPath = childScript.get("path");
		String childName = childScript.get("name");

		return childPath + childName;
	}
}
