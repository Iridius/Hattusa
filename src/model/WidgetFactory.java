package model;

import java.util.HashMap;

public class WidgetFactory {
    public static Widget create(final HashMap<String, String> params) {
        String type = extractValue(params, "type");
        String name = extractValue(params, "name");
        int posX = Integer.parseInt(extractValue(params, "posX"));
        int posY = Integer.parseInt(extractValue(params, "posY"));

        if(type.toLowerCase().equals("tree")) {
            return new Tree(name, posX, posY);
        }

        return null;
    }

    private static String extractValue(final HashMap<String, String> map, final String field) {
        String result = "";

        try {
            result = map.get(field);
        }
        catch(Exception exc) {
            exc.printStackTrace();
        }

        return result;
    }
}
