package model;

import controller.TestFramework;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ScriptTest {
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
}