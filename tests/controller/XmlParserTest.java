package controller;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class XmlParserTest {
    private final String getLiterature() {
        return "<?xml version=\"1.0\" encoding=\"windows-1251\"?>\n" +
                "<attributes>\n" +
                "\t<output>true</output>\n" +
                "\t<MainTemplate>{$Templates}/main.template</MainTemplate>\n" +
                "\t<CurrentPage>Литература</CurrentPage>\n" +
                "\t<Breadcrumbs>{$Breadcrumbs}/mainpath.thtml</Breadcrumbs>\n" +
                "\t<MainMenu>{$Templates}/mm_literature.template</MainMenu>\n" +
                "\t<Content>\n" +
                "\t\t<from><![CDATA[<td colspan=\"4\">]]></from>\n" +
                "\t\t<to><![CDATA[</table>]]></to>\n" +
                "\t\t<value>{$Blanks}/para.blank</value>\n" +
                "\t\t<path>{$BasePath}\\literatute.thtml</path>\n" +
                "\t</Content>\n" +
                "</attributes>";
    }

    @Test
    public void testGetContent() {
        XmlParser parser = new XmlParser(getLiterature());
        HashMap<String, HashMap<String, String>> result = parser.getContent();

        assertNotNull("Expected non-empty xml-content.", result);
        assertEquals("Expected 6 elements in xml-attributes collection.", 6, result.size());
        assertEquals("Expected element \"output\" will be value \"true\" ", "true", result.get("output").get("value").toString());
    }
}