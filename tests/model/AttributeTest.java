package model;

import controller.TestFramework;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AttributeTest {
	@Before
	public void init(){
		Config.setProjectPath(TestFramework.getSourcePath());
	}

	@Test
	public void test_isSimple_plain_value(){
		Attribute attribute = new Attribute("output");
		attribute.put("value", "false");

		assertTrue("Expected 'output'-attribute will be simple.", attribute.isSimple());
	}

	@Test
	public void test_isSimple_search_value(){
		Attribute attribute = new Attribute("author");
		attribute.put("sys:from", "<![CDATA[<strong>]]>");
		attribute.put("sys:to", "<![CDATA[</strong>]]>");

		assertTrue("Expected 'author'-attribute will be simple-search.", attribute.isSimple());
	}

	@Test
	public void test_isSimple_search_with_template_value(){
		Attribute attribute = new Attribute("author");
		attribute.put("sys:from", "<![CDATA[<strong>]]>");
		attribute.put("sys:to", "<![CDATA[</strong>]]>");
		attribute.put("template", "{$Templates}\\Literature\\Author.template");

		assertTrue("Expected attribute will be simple-search-with-template.", attribute.isSimple());
	}

	@Test
	public void test_isSimple_false() {
		Attribute attribute = new Attribute("author");
		attribute.put("value", "{$BasePath}\\Literature\\Books\\Ассирия");
		attribute.put("filter", "thtml");

		assertFalse("Expected non-system attribute with tag 'value' will be complex.", attribute.isSimple());
	}

	@Test
	public void test_isSimple_system(){
		Attribute attribute = new Attribute("sys:path");
		attribute.put("value", "{$BasePath}\\literature.thtml");

		assertTrue("Expected \"sys:path\" attribute will be simple.", attribute.isSimple());
	}

	@Test
	public void test_isSimple_non_processing_path_value(){
		Attribute attribute = new Attribute("MainTemplate");
		attribute.put("value", "{$Templates}\\Literature\\Author.template");

		assertTrue("Expected non-processing path value s.a. *.template will be simple.", attribute.isSimple());
	}

	@Test
	public void test_prepareValue(){
		final String expected = "expected text";
		final String text = "<strong>" + expected + "</strong>";

		Attribute attribute = new Attribute("author");
		attribute.put("sys:from", "<![CDATA[<strong>]]>");
		attribute.put("sys:to", "<![CDATA[</strong>]]>");
		attribute.prepare(text);

		assertEquals("Expected only one prepared attribute.", 1, attribute.getKeys().size());
		assertEquals("Expected prepared attribute value.", expected, attribute.get("value"));
	}

	@Test
	public void test_get_missing_tag(){
		Script script = TestFramework.getScript();

		final String expected = "";
		final String actual = script.get("param").get("missing_tag");

		assertEquals("Expected void value for missing tag.", expected, actual);
	}

	@Test
	public void test_put_replacing_value(){
		Attribute attribute = new Attribute("output");
		attribute.put("value", "false");
		attribute.put("value", "true");

		assertTrue("Expected one value from double.", attribute.getKeys().size() == 1);
		assertTrue("Expected 1st value will be replaced by 2nd.", attribute.get("value").equalsIgnoreCase("true"));
	}

//	@Test
//	public void test_get_child_attributes_count(){
//		String text = TestFramework.getLiteratuteText();
//
//		Attribute attribute = new Attribute("Content");
//		attribute.put("sys:from", "<td colspan=\"4\">");
//		attribute.put("sys:to", "</table>");
//		attribute.put("value", "{$Blanks}\\Literature\\para.blank");
//
//		FileData data = attribute.getChildScripts(text);
//
//		assertEquals("Expected 2 items in result data.", 2, data.size());
//	}
}