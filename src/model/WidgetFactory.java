package model;

public class WidgetFactory {
    public static Widget create(String type, String source) {
        if(type.toLowerCase().equals("tree")) {
            return new Tree(source);
        }

        return null;
    }
}
