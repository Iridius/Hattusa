package model;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Script implements IData<Attribute> {
	private Map<String, Attribute> _script;

	public Script() {
		_script = new LinkedHashMap();
	}

	@Override
	public void put(String name, Attribute value) {
		_script.put(name, value);
	}

	@Override
	public int size() {
		return _script.size();
	}

	public boolean isSimple(String key) {
		Attribute attribute = _script.get(key);

		return attribute.size() == 1 && attribute.getKeys().contains("value");
	}

	public int index(final String key) {
		int i = 0;
		for(String ks: _script.keySet()){
			if(ks.equalsIgnoreCase(key)){
				return i;
			}

			i++;
		}

		return -1;
	}

	@Override
	public Collection<String> getKeys() {
		return _script.keySet();
	}

	@Override
	public String get(String key) {
		if(key.indexOf(".") <= 0) {
			return _script.get(key).get("value");
		}

		final String tag = key.substring(0, key.indexOf("."));
		final String attribute = key.substring(tag.length() + 1);

		for(String k: _script.keySet()){
			if(k.equalsIgnoreCase(tag)){
				return _script.get(k).get(attribute);
			}
		}

		return "";
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
