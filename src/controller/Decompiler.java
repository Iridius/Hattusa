package controller;

import model.Attribute;
import model.Config;
import model.FileData;
import model.Script;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.LinkedList;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Alexandria.Library.getContent;

public class Decompiler {
	private final static Logger log = Logger.getLogger(Decompiler.class.getName());
	private final static String _PATH = "sys:path";

	public static FileData run(final String text, final Script script) {
		FileData result = new FileData();

        //result.put(run_script(text, script));
		//result.put(run_child_scripts(text, script));

		return result;
	}

//	private static FileData run_child_scripts(final String text, final Script script) {
//		FileData result = new FileData();
//
//		for(Attribute attribute: script.getAttributes()){
//			if(attribute.isSimple()){
//				continue;
//			}
//
//			for(Script childScript: getScripts(text, attribute)){
//				String path = childScript.get("sys:path").get("value");
//				String content = childScript.toString();
//
//				if(path.length() != 0 && content.length() != 0) {
//					result.put(path, content);
//				}
//			}
//		}
//
//		return result;
//	}

	private static Collection<Script> getScripts(final String text, final Attribute attribute) {
		Collection<Script> result = new LinkedList();

		String childPath = attribute.get("value");
		if(childPath.length() == 0){
			return result;
		}

		XmlParser parser = new XmlParser(Paths.get(Config.prepareValue(childPath)));
		Script childScript = parser.getScript();
		int childScriptCount = getChildScriptCount(text, childScript);
		if(childScriptCount == 0){
			return result;
		}

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

//	private static FileData run_script(final String text, final Script script) {
//		FileData result = new FileData();
//
//		//String path = getPath(script);
//		//String content = getContent(text, script);
//		Script compiledScript = getScript(text, script);
//
//		//if(path.length() != 0 && content.length() != 0) {
//		//	result.put(path, content);
//		//}
//		result.add(compiledScript);
//
//
//		return result;
//	}

//	private static String getPath(Script script) {
//		return script.get("sys:path").get("value");
//	}

	private static FileData getScript(final String text, final Script source) {
		FileData result = new FileData();

		Script outputScript = new Script();
		String outputPath = source.get("sys:path").get("value");

		for(Attribute attribute: source.getAttributes()){
			if(attribute.isSystem()){
				continue;
			}
			if(attribute.isSimple()){
				attribute.prepareValue(text);
				outputScript.put(attribute.getName(), attribute);
				continue;
			}

			result.put(attribute.getChildScripts());
		}

		result.put(outputPath, outputScript);
		return result;
	}

//	private static String getComplexValue(Attribute attribute) {
//		String result = "";
//		for(String key: attribute.getKeys()){
//			if(attribute.isSystem(key)){
//				continue;
//			}
//
//			String value = attribute.get(key);
//			if(key.equalsIgnoreCase("value")){
//				value = getValuePath(value);
//			}
//
//			result += "\n\t\t<" + key + ">" + value + "</" + key + ">";
//		}
//
//		return result;
//	}
//
//	private static String getValuePath(final String value) {
//		Path valuePath = Paths.get(Config.prepareValue(value));
//		XmlParser parser = new XmlParser(valuePath);
//
//		return getPath(parser.getScript());
//	}
}
