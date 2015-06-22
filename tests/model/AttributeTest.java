package model;

import controller.TestFramework;
import org.junit.Test;
import static org.junit.Assert.*;

public class AttributeTest {
	@Test
	public void isSimple_plain_value(){
		Config.setProjectPath(TestFramework.getSourcePath());
		Attribute attribute = new Attribute("output");
		attribute.put("value", "false");

		assertTrue("Expected 'output'-attribute will be simple.", attribute.isSimple());
	}

	@Test
	public void isSimple_search_value(){
		Attribute attribute = new Attribute("author");
		attribute.put("sys:from", "<![CDATA[<strong>]]>");
		attribute.put("sys:to", "<![CDATA[</strong>]]>");

		assertTrue("Expected 'author'-attribute will be simple-search.", attribute.isSimple());
	}

	@Test
	public void isSimple_search_with_template_value(){
		Attribute attribute = new Attribute("author");
		attribute.put("sys:from", "<![CDATA[<strong>]]>");
		attribute.put("sys:to", "<![CDATA[</strong>]]>");
		attribute.put("template", "{$Templates}\\Literature\\Author.template");

		assertTrue("Expected attribute will be simple-search-with-template.", attribute.isSimple());
	}

	@Test
	public void isSimple_false() {
		Config.setProjectPath(TestFramework.getSourcePath());
		Attribute attribute = new Attribute("author");
		attribute.put("value", "{$BasePath}\\Literature\\Books\\Ассирия");
		attribute.put("filter", "thtml");

		assertFalse("Expected non-system attribute with tag 'value' will be complex.", attribute.isSimple());
	}

	@Test
	public void isSimple_system(){
		Attribute attribute = new Attribute("sys:path");
		attribute.put("value", "{$BasePath}\\literature.thtml");

		assertTrue("Expected \"sys:path\" attribute will be simple.", attribute.isSimple());
	}

	@Test
	public void isSimple_non_processing_path_value(){
		Attribute attribute = new Attribute("MainTemplate");
		attribute.put("value", "{$Templates}\\Literature\\Author.template");

		assertTrue("Expected non-processing path value s.a. *.template will be simple.", attribute.isSimple());
	}
}