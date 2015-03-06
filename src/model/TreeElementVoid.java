package model;

import Alexandria.Library;

import java.nio.file.Paths;

public class TreeElementVoid implements ITreeElement {
    private String _fullName;
    private boolean _isFolder;

    public TreeElementVoid(String fullName) {
        _fullName = fullName;
        _isFolder = Library.isFolder(Paths.get(_fullName));
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public String getFullName() {
        return _fullName;
    }

    @Override
    public ITreeElement getParent() {
        return null;
    }

    @Override
    public boolean isRoot() {
        return true;
    }

    @Override
    public boolean isFolder() {
        return false;
    }
}
