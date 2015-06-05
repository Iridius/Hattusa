package controller;

import model.Script;

import java.util.logging.Logger;

public class Decompiler {
	private final static Logger log = Logger.getLogger(Decompiler.class.getName());
	private final String _text;
	private final Script _script;

	public Decompiler(String text, Script script) {
		_text = text;
		_script = script;

	}

	public String run() {
		return "";
	}
}
