package controller;

import Alexandria.Library;
import model.Attribute;
import model.Script;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Path;
import java.util.logging.Logger;

public class XmlParser {
	private final static Logger log = Logger.getLogger(XmlParser.class.getName());
	private final String _text;

	public XmlParser(Path path){
		_text = Library.getContent(path);
	}

	public XmlParser(String text) {
		_text = text;
	}

	public Script getScript() {
		Script script = new Script();

		NodeList attributes = getNodeList();
		final int attributesCount = attributes.getLength();
		if(attributesCount == 0){
			return script;
		}

		for(int i = 0; i < attributesCount; i++) {
			if(attributes.item(i).getNodeType() == Node.ELEMENT_NODE) {
				Node node = attributes.item(i);
				script.put(
						getName(node),
						getValue(node)
				);
			}
		}

		return script;
	}

	private Attribute getValue(Node node) {
		Attribute result = new Attribute();

		NodeList childNodes = node.getChildNodes();
		for(int j = 0; j < childNodes.getLength(); j++){
            if(childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element)childNodes.item(j);
                result.put(
						element.getNodeName(),
						element.getTextContent()
				);
            }
        }

		if(result.isEmpty()) {
			result.put("value", node.getTextContent());
		}

		return result;
	}

	private String getName(Node node) {
		return node.getNodeName();
	}

	private NodeList getNodeList() {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			log.severe(e.getMessage());
		}

		Document document = null;
		try {
			document = dBuilder.parse(new InputSource(new StringReader(_text)));
		} catch (Exception e) {
			log.severe(e.getMessage());
		}

		document.getDocumentElement().normalize();
		return document.getDocumentElement().getChildNodes();
	}
}
