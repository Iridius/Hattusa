package model;

import org.json.JSONArray;

public class WidgetFactory {
    public static Widget create(String type, String source, String posX, String posY) {
        if(type.toLowerCase().equals("tree")) {
            return new Tree(source);
        }

        return null;
    }
}
