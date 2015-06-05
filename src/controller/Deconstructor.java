package controller;

import java.util.HashMap;
import java.util.logging.Logger;

public class Deconstructor {
	private final static Logger log = Logger.getLogger(Deconstructor.class.getName());
	private final String _text;
	private final HashMap<String, HashMap<String, String>> _script;

	public Deconstructor(String text, HashMap<String, HashMap<String, String>> script) {
		_text = text;
		_script = script;

	}

	public String run() {
		String result = "";


		return result;
	}
}
