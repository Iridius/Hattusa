package model;

import Alexandria.Library;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.nio.file.Path;

public class JsonParser {
    public static Frame parseFrame(Path path) {
        try {
            String fileContent = Library.getContent(path);
            JSONObject obj = new JSONObject(fileContent);

            String name = obj.getString("name");
            int width = obj.getJSONObject("layout").getInt("width");
            int height = obj.getJSONObject("layout").getInt("height");

            Frame frame = new Frame(name, width, height);

            JSONArray arr = obj.getJSONArray("widgets");
            for (int i = 0; i < arr.length(); i++)
            {
                String type = arr.getJSONObject(i).getString("type");
                String source = arr.getJSONObject(i).getString("source");

                Widget widget = WidgetFactory.create(type, source);
                frame.add(widget);
            }

            return frame;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
