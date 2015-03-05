package model;

public class TreeElementVoid implements ITreeElement {
    private String _fullName;

    public TreeElementVoid(String fullName) {
        _fullName = fullName;
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
}
