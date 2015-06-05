package controller;

import model.Script;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class XmlParserTest {

    @Test
    public void testGetContent() {
        XmlParser parser = new XmlParser(TestFramework.getLiteratureScript().toString());
        Script result = parser.getContent();

        assertNotNull("Expected non-empty xml-content.", result);
        assertEquals("Expected 7 elements in xml-attributes collection.", 7, result.size());
        assertEquals("Expected element \"output\" will be value \"true\" ", "true", result.get("output.value"));
    }
}