package model;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileData implements IData<Script> {
	private Map<String, Script> _files;

	public FileData() {
		_files = new LinkedHashMap<>();
	}

	@Override
	public Collection<String> getKeys() {
		return _files.keySet();
	}

	@Override
	public Script get(String key) {
		return _files.get(key);
	}

	@Override
	public void put(String name, Script value) {
		_files.put(name, value);
	}

	@Override
	public int size() {
		return _files.size();
	}

	public Script getFirst(){
		if(_files.size() > 0){
			for(String name: _files.keySet()){
				return _files.get(name);
			}
		}

		return new Script();
	}

	public void put(FileData data) {
		;
	}
}
