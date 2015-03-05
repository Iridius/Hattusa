package model;

public class TreeElementImpl implements ITreeElement {
    private String _name;
    private ITreeElement _parent;

    public TreeElementImpl(String name, ITreeElement parent) {
        _name = name;
        _parent = parent;
    }

    @Override
    public String getName() {
        return _name;
    }

    @Override
    public ITreeElement getParent() {
        return _parent;
    }

    @Override
    public boolean isRoot() {
        return _parent.equals(null);
    }
}
