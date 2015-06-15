package controller;

import model.Attribute;
import model.Script;

import java.util.logging.Logger;

public class Decompiler {
	private final static Logger log = Logger.getLogger(Decompiler.class.getName());
	private final String _text;
	private final Script _script;
	private final String _output;
	private final String _mainTemplate;


	public Decompiler(String text, Script script) {
		_text = text;
		_script = script;
		_output = _script.get("output");
		_mainTemplate = _script.get("mainTemplate");
	}

	public String run() {
		String result = "<?xml version=\"1.0\" encoding=\"windows-1251\"?>";
		result += "\n<attributes>";

		for(String attribute: _script.getKeys()){
			if(_script.isSimple(attribute)){
				result += "\n\t<" + attribute + ">" + _script.get(attribute) + "</" + attribute + ">";
			}
		}

		result += "\n</attributes>";
		return result;
	}
}
