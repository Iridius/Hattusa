package controller;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.logging.Logger;

public class XmlParser {
	private final static Logger log = Logger.getLogger(XmlParser.class.getName());
	private final String _text;

	public XmlParser(String text) {
		_text = text;
	}

	public HashMap<String, HashMap<String, String>> getContent() {
		HashMap<String, HashMap<String, String>> result = new HashMap<>();
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			log.severe(e.getMessage());
			return result;
		}

		Document document;
		try {
			document = dBuilder.parse(new InputSource(new StringReader(_text)));
		} catch (SAXException e) {
			log.severe(e.getMessage());
			return result;
		} catch (IOException e) {
			log.severe(e.getMessage());
			return result;
		}
		document.getDocumentElement().normalize();
		NodeList attributes = document.getDocumentElement().getChildNodes();
		if(attributes.getLength() > 0) {
			for(int i = 0; i < attributes.getLength(); i++) {
				if(attributes.item(i).getNodeType() == Node.ELEMENT_NODE) {
					String attributeName = "";
					HashMap<String, String> properties = new HashMap<>();
					NodeList attrubuteValues = attributes.item(i).getChildNodes();

					for(int j = 0; j < attrubuteValues.getLength(); j++){
						if(attrubuteValues.item(j).getNodeType() == Node.ELEMENT_NODE) {
							Element element = (Element)attrubuteValues.item(j);
							String propertyName = element.getNodeName();
							String propertyValue = element.getTextContent();

							if(propertyName.equals("name")) {
								attributeName = propertyValue;
								continue;
							}
							properties.put(propertyName, propertyValue);
						}
					}

					if(attributeName.length() == 0) {
						log.info("One or same attributes has't 'name'-property.");
						return result;
					}

					result.put(attributeName, properties);
			   }
			}
		}

		return result;
	}
}
