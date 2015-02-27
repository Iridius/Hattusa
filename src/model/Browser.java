package model;

public class Browser implements Widget {
    private String _name;
    private String _source;
    private int _posX;
    private int _posY;

    public Browser(String name, String source, int posX, int posY) {
        _name = name;
        _source = source;
        _posX = posX;
        _posY = posY;
    }

    @Override
    public String getType() {
        return "browser";
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
        if(this.equals(null)) {
            return false;
        }

        if(_name.isEmpty() || _posX == 0 || _posY == 0) {
            return false;
        }

        return true;
    }
}
