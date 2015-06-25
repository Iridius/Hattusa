package model;

import controller.TestFramework;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class ScriptTest {
	@Before
	public void init(){
		Config.setProjectPath(TestFramework.getSourcePath());
	}

	@Test
	public void testPut(){
		Script script = TestFramework.getScript();

		assertTrue("Expected put-method creates one more records in script.", script.size() > 0);
	}

	@Test
	public void testSize(){
		Script script = TestFramework.getScript();

		assertEquals("Expected put-method creates 1 record in script.", 1, script.size());
	}

	@Test
	public void testGet(){
		final Script script = TestFramework.getScript();

		final String expected = "value";
		final String actual = script.get("param").get("attribute");

		assertEquals("Expected one attribute in script with name \"param.attribute\", value: \"value\".", expected, actual);
	}

	@Test
	public void testGet_ignore_case(){
		final Script script = TestFramework.getScript();

		final String expected = "value";
		final String actual = script.get("Param").get("attribute");

		assertEquals("Expected getting value without case.", expected, actual);
	}

	@Test
	public void test_run(){
		String text = TestFramework.getLiteratuteText();
		Script source = TestFramework.getLiteratureBlankScript();

		String actual = source.run(text);
		String expected = TestFramework.getLiteratureScript().toString();

		assertEquals("Expected another value processed blank script", expected, actual);
	}

	@Test
	public void test_run_subscripts_count(){
		String text = TestFramework.getLiteratuteText();
		Script source = TestFramework.getLiteratureBlankScript();

		source.run(text);
		int actual = source.getScripts().size();
		int expected = 2;

		assertEquals("Expected 2 subscripts in 'literature'-script.", expected, actual);
	}

	@Test
	public void test_run_subscript_name(){
		String text = TestFramework.getLiteratuteText();
		Script source = TestFramework.getLiteratureBlankScript();

		source.run(text);
		Script child = source.getScript(0);
		String actual = child.get("name").get("value");
		String expected = "Инки1";

		assertEquals("Expected another 'name'-property value in 1st subscript.", expected, actual);
	}
}