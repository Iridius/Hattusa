package model;

import java.util.HashMap;

public class WidgetFactory {
    public static Widget create(final HashMap<String, String> params) {
        try {
            String type = extractValue(params, "type");
            String source = extractValue(params, "source");
            String name = extractValue(params, "name");
            int posX = Integer.parseInt(extractValue(params, "posX"));
            int posY = Integer.parseInt(extractValue(params, "posY"));

            if (type.toLowerCase().equals("tree")) {
                return new Tree(name, posX, posY);
            }
            if (type.toLowerCase().equals("browser")) {
                return new Browser(name, source, posX, posY);
            }

        } catch (HattusaNotExistsMainParameters exc) {
            ;
        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return null;
    }

    private static String extractValue(final HashMap<String, String> map, final String field) throws HattusaNotExistsMainParameters {
        String result = "";

        try {
            result = map.get(field);

            if(result.equals(null)) {
                throw new NullPointerException();
            }
        } catch (NullPointerException exc) {
            throw new HattusaNotExistsMainParameters("Required field [" + field + "] not found.");
        }

        return result;
    }
}
