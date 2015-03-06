package model;

import Alexandria.Library;

import java.nio.file.Paths;

public class TreeElementImpl implements ITreeElement {
    private String _name;
    private String _fullName;
    private ITreeElement _parent;

    public TreeElementImpl(String name, String fullName, ITreeElement parent) {
        _name = name;
        _fullName = fullName;
        _parent = parent;
    }

    @Override
    public String getName() {
        return _name;
    }

    @Override
    public String getFullName() {
        return _fullName;
    }

    @Override
    public ITreeElement getParent() {
        return _parent;
    }

    @Override
    public boolean isRoot() {
        return _parent instanceof TreeElementVoid;
    }

    @Override
    public boolean isFolder() {
        return Library.isFolder(Paths.get(_fullName));
    }
}
