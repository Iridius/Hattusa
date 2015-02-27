package model;

import java.util.ArrayList;
import java.util.Collection;

public class FrameModel {
    private int _width;
    public int getWidth() {
        return _width;
    }

    private int _height;
    public int getHeight() {
        return _height;
    }

    private String _name;
    public String getName() {
        return _name;
    }

    private Collection<Widget> _widgets;

    public FrameModel(String name, int width, int height) {
        _name = name;
        _width = width;
        _height = height;
        _widgets = new ArrayList<Widget>();
    }

    public void add(Widget widget) {
        _widgets.add(widget);
    }

    public void addRange(Collection<Widget> widgets) {
        for(Widget widget: widgets) {
            if(widget.isValid()) {
                _widgets.add(widget);
            }
        }
    }
}