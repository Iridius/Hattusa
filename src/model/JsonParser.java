package model;

import Alexandria.Library;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Iterator;

public class JsonParser {
    public static FrameModel parseFrame(Path path) {
        try {
            String fileContent = Library.getContent(path);
            JSONObject obj = new JSONObject(fileContent);

            String name = obj.getString("name");
            int width = obj.getJSONObject("layout").getInt("width");
            int height = obj.getJSONObject("layout").getInt("height");

            FrameModel frame = new FrameModel(name, width, height);

            JSONArray arr = obj.getJSONArray("widgets");
            HashMap<String, String> params = new HashMap<String, String>();
            for (int i = 0; i < arr.length(); i++)
            {
                //String type = arr.getJSONObject(i).getString("type");
                //String source = arr.getJSONObject(i).getString("source");
                //String posX = arr.getJSONObject(i).getString("posX");
                //String posY = arr.getJSONObject(i).getString("posY");
                Iterator<?> keys = arr.getJSONObject(i).keys();

                while(keys.hasNext()) {

                }

                //Widget widget = WidgetFactory.create(type, source, posX, posY);
                //frame.add(widget);
            }

            return frame;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
