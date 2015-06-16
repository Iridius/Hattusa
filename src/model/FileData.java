package model;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileData implements IData<String> {
	private Map<String, String> _files;

	public FileData() {
		_files = new LinkedHashMap<>();
	}

	@Override
	public Collection<String> getKeys() {
		return _files.keySet();
	}

	@Override
	public String get(String key) {
		return _files.get(key);
	}

	@Override
	public void put(String name, String value) {
		_files.put(name, value);
	}

	@Override
	public int size() {
		return _files.size();
	}

	@Override
	public boolean isSystem(String key) {
		return false;
	}

	public String getFirst(){
		if(_files.size() > 0){
			for(String name: _files.keySet()){
				return _files.get(name);
			}
		}

		return "";
	}
}
