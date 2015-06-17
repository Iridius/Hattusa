package controller;

import Alexandria.Library;
import model.Attribute;
import model.Config;
import model.FileData;
import model.Script;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class Decompiler {
	private final static Logger log = Logger.getLogger(Decompiler.class.getName());
	private final String _text;
	private final Script _script;

	public Decompiler(String text, Script script) {
		_text = text;
		_script = script;
	}

	public FileData run() {
		FileData result = new FileData();

		String path = _script.getPath().toString();
		String content = getContent();

		result.put(path, content);
		return result;
	}

	private String getContent() {
		String content = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
		content += "\n<attributes>";
		for(String key: _script.getKeys()){
			if(_script.isSystem(key)){
				continue;
			}

			Attribute attribute = _script.get(key);
			if(attribute.isSimple()){
				content += "\n\t<" + key + ">" + attribute.get("value") + "</" + key + ">";
			}
			else{
				content += "\n\t<" + key + ">";
				content += getComplexValue(attribute);
				content += "\n\t</" + key + ">";

				runChild(attribute);
			}
		}
		content += "\n</attributes>";
		return content;
	}

	private void runChild(Attribute attribute) {
		final String text = getText(attribute);
		if(text.length() == 0){
			return;
		}

//		String value = Config.prepareValue(attribute.get("value"));
//		if(value.length() != 0 && Library.isPath(value)){
//			XmlParser parser = new XmlParser(Paths.get(value));
//			Script script = parser.getScript();
//		}


	}

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

	private String getText(Attribute attribute) {
		final String from = attribute.get("from");
		final String to = attribute.get("to");

		if(from.length() == 0){
			return "";
		}

		final int pos_from = _text.indexOf(from) + from.length();
		final int pos_to = to.length() != 0 ? _text.indexOf(to, pos_from) : _text.length();

		return _text.substring(pos_from, pos_to);
	}

	private String getValuePath(final String value) {
		Path valuePath = Paths.get(Config.prepareValue(value));
		XmlParser parser = new XmlParser(valuePath);

		return parser.getScript().get("sys:path").get("value");
	}
}
