package model;

import Alexandria.Library;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class JsonParser {
    public static FrameModel parseFrame(Path path) {
        try {
            JSONObject obj = new JSONObject(Library.getContent(path));
            FrameModel frame = new FrameModel(
                obj.getString("name"),
                obj.getJSONObject("layout").getInt("width"),
                obj.getJSONObject("layout").getInt("height")
            );

            Collection<Widget> widgets = getWidgets(obj.getJSONArray("widgets"));
            frame.addRange(widgets);
            return frame;
        }
        catch (JSONException e) {
            ;
        }

        return null;
    }

    private static Collection<Widget> getWidgets(JSONArray arr) throws JSONException {
        Collection<Widget> result = new ArrayList<Widget>();

        for (int i = 0; i < arr.length(); i++)
        {
            JSONObject objParams = arr.getJSONObject(i);
            HashMap<String, String> params = new HashMap<String, String>();

            Iterator<?> keys = objParams.keys();
            while(keys.hasNext()) {
                String key = (String)keys.next();
               // if(objParams.get(key) instanceof JSJSONObject ){

                    params.put(key, objParams.getString(key));
               // }
            }

            Widget widget = WidgetFactory.create(params);
            result.add(widget);
        }

        return result;
    }
}
