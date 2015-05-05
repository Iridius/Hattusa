package model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.Node;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class Config {
    private final static Logger log = Logger.getLogger(Config.class.getName());
    //private final static Path _configPath = Paths.get(System.getProperty("user.home"), "hattusa.cfg");
    private final static Path _configPath = Paths.get("hattusa.xml");

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
            File file = _configPath.toAbsolutePath().toFile();
            if(!file.exists()) {
                throw new Exception("Config file not exist.");
            }
            Document document = dBuilder.parse(file);
            document.getDocumentElement().normalize();

            NodeList childNodes = document.getDocumentElement().getChildNodes();
            if(childNodes.getLength() > 0) {
                for(int i = 0; i < childNodes.getLength(); i++) {
                    if(childNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element)childNodes.item(i);

                        if(element.getNodeName().equals("lastproject")) {
                            String value = element.getTextContent();
                            _lastProjectPath = Paths.get(value);
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.severe(e.getMessage());
        }
    }

    public static Path getLastProjectPath() {
        return getConfig()._lastProjectPath;
    }
}
