package framework;
import java.util.HashMap;

public class TestFramework {
    public static HashMap<String, String> getWidgetParams() {
        HashMap<String, String> result = new HashMap<String, String>() {
            {
                put("type", "Tree");
                put("name", "Source tree");
                put("source", "{@Source}");
                put("posX", "1");
                put("posY", "1");
            }
        };

        return result;
    }
}
