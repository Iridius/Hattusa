package model;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class Config {
    private final static Logger log = Logger.getLogger(Config.class.getName());
    private final static Path _configPath = Paths.get(System.getProperty("user.home"), "hattusa.cfg");

    private static Config _CONFIG;
    private static Path _lastProjectPath;

    private static Config getConfig() {
        if(_CONFIG == null) {
            _CONFIG = new Config();

            parseConfig();
        }

        return _CONFIG;
    }

    private static void parseConfig() {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(_configPath.toFile());

            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("lastproject");
            if(nList.getLength() > 0) {
                _lastProjectPath = Paths.get(nList.item(0).getNodeValue().toString());

            }
        } catch (Exception e) {
            log.severe(e.getMessage());
        }
    }

    public static Path getLastProjectPath() {
        return getConfig()._lastProjectPath;
    }
}
