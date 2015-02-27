package model;

public class Tree implements Widget {
    private String _name;
    private String _source;
    private int _posX;
    private int _posY;

    public Tree(String name, int posX, int posY) {
        _name = name;
        _posX = posX;
        _posY = posY;
    }

    @Override
    public String getType() {
        return "tree";
    }

    @Override
    public String getName() {
        return _name;
    }

    @Override
    public int getX() {
        return _posX;
    }

    @Override
    public int getY() {
        return _posY;
    }

    @Override
    public boolean isValid() {
        if(!_name.isEmpty() && _posX != 0 && _posY != 0) {
            return true;
        }

        return false;
    }
}
