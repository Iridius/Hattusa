package controller;

import model.Script;
import org.junit.Test;

import static org.junit.Assert.*;

public class XmlParserTest {

    @Test
    public void testGetContent() {
        XmlParser parser = new XmlParser(TestFramework.getLiteratureScript().toString());
        Script result = parser.getScript();

        assertNotNull("Expected non-empty xml-content.", result);
        assertEquals("Expected 6 elements in xml-attributes collection.", 6, result.size());
        assertEquals("Expected element \"output\" will be value \"true\" ", "true", result.get("output").get("value"));
    }
}