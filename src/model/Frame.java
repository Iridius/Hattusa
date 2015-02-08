package model;

import java.util.ArrayList;
import java.util.Collection;

public class Frame {
    private int _width;
    private int _height;
    private String _name;
    public String getName() {
        return _name;
    }

    private Collection<Widget> _widgets;

    public Frame(String name, int width, int height) {
        _name = name;
        _width = width;
        _height = height;
        _widgets = new ArrayList<Widget>();
    }

    public void add(Widget widget) {
        _widgets.add(widget);
    }
}