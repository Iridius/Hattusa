package controller;

import model.Attribute;
import model.Config;
import model.FileData;
import model.Script;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class Decompiler {
	private final static Logger log = Logger.getLogger(Decompiler.class.getName());

	public static FileData run(final String text, final Script script) {
		FileData result = new FileData();

        result.put(run_script(text, script));
		result.put(run_child_scripts(text, script));

		return result;
	}

	private static FileData run_child_scripts(final String text, final Script script) {
		FileData result = new FileData();

		for(Attribute attribute: script.getAttributes()){
			if(attribute.isSimple()){
				continue;
			}

//			for(Script script: getScripts(attribute)){
//				path = script.get("path");
//				content = getContent(script);
//
//				if(path.length() != 0 && content.length() != 0) {
//					result.put(getContent(attribute));
//				}
//			}
		}

		return result;
	}

	private static FileData run_script(final String text, final Script script) {
		FileData result = new FileData();

		String path = script.getPath().toString();
		String content = getContent(text, script);
		if(path.length() != 0 && content.length() != 0) {
			result.put(path, content);
		}

		return result;
	}

	private static String getContent(final String text, final Script script) {
		String content = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
		content += "\n<attributes>";
		for(String key: script.getKeys()){
			if(script.isSystem(key)){
				continue;
			}

			Attribute attribute = script.get(key);
			if(attribute.isSimple()){
				content += "\n\t<" + key + ">" + attribute.getValue(text) + "</" + key + ">";
			}
			else{
				content += "\n\t<" + key + ">";
				content += getComplexValue(attribute);
				content += "\n\t</" + key + ">";
			}
		}
		content += "\n</attributes>";
		return content;
	}

//	private FileData getContent(Attribute attribute) {
//		FileData result = new FileData();
//
//		final String text = getText(attribute);
//		if(text.length() == 0){
//			return result;
//		}
//
//		String value = Config.prepareValue(attribute.get("value"));
//		if(!value.endsWith(".blank")){
//			return result;
//		}
//		if(!Library.isPath(value)){
//			return result;
//		}
//
//		Path path = Paths.get(value);
//		if(!Library.isFile(path)){
//			return  result;
//		}
//
//		//final String parent_name = attribute.get("name");
//
//		XmlParser parser = new XmlParser(Paths.get(value));
//		Script script = parser.getScript();
//
//		Decompiler decompiler = new Decompiler(text, script);
//		return decompiler.run();
//	}

	private static String getComplexValue(Attribute attribute) {
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

//	private String getText(Attribute attribute) {
//		final String from = attribute.get("sys:from");
//		final String to = attribute.get("sys:to");
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

	private static String getValuePath(final String value) {
		Path valuePath = Paths.get(Config.prepareValue(value));
		XmlParser parser = new XmlParser(valuePath);

		return parser.getScript().get("sys:path").get("value");
	}
}
