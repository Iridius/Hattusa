package model;

import java.util.HashMap;

public class Script implements IData<Attribute> {
	private HashMap<String, Attribute> _script;

	public Script() {
		_script = new HashMap<>();
	}

	@Override
	public void put(String name, Attribute value) {
		_script.put(name.toLowerCase(), value);
	}

	@Override
	public int size() {
		return _script.size();
	}

	@Override
	public boolean isEmpty() {
		return _script.size() == 0;
	}

	@Override
	public String get(String key) {
		key = key.toLowerCase();

		if(key.indexOf(".") <= 0) {
			return _script.get(key).get("value");
		}

		final String tag = key.substring(0, key.indexOf("."));
		final String attribute = key.substring(tag.length() + 1);

		return _script.get(tag).get(attribute);
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
}
