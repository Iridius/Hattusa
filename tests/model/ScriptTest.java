package model;

import controller.TestFramework;
import controller.Utils;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
	public void test_run() throws Exception {
		String text = TestFramework.getLiteratuteText();
		Script source = TestFramework.getLiteratureBlankScript();

		String actual = source.run(text).toString();
		String expected = TestFramework.getLiteratureScript().toString();

		assertEquals("Expected another value processed blank script", expected, actual);
	}

	@Test
		 public void test_run_one_level_path(){
		String text = TestFramework.getLiteratuteText();
		Script source = TestFramework.getLiteratureBlankScript();

		String expected = "{$BasePath}\\literature.thtml";
		String actual = source.run(text).get("sys:path").get("value");

		assertEquals("Expected another 1st level script path.", expected, actual);
	}

	@Test
	public void test_run_two_level_path(){
		String text = TestFramework.getLiteratuteText();
		Script source = TestFramework.getLiteratureBlankScript();
		Script output = source.run(text);

		String expected = "{$BasePath}\\Literature\\Para\\Инки.thtml";
		String actual = output.getScript(0).get("sys:path").get("value");

		assertEquals("Expected another 2nd level script path.", expected, actual);
	}

	@Test
	public void test_run_one_level_script() throws Exception {
		final Script source = TestFramework.getBookScript();
		final String scope = "<strong>Альперович М. С., Слезкин Л. Ю.</strong> <em>История Латинской Америки (с древнейших времен до начала XX в.).</em> М., &laquo;Высшая школа&raquo;, 1991.";

		String expected = TestFramework.getBookScriptText();
		String actual = source.run(scope).toString();

		assertEquals("Expected another script-processing result.", expected, actual);
	}

	@Test
	public void test_run_subscripts_count() throws Exception {
		String text = TestFramework.getLiteratuteText();
		Script source = TestFramework.getLiteratureBlankScript();
		Script output = source.run(text);

		int actual = output.getScripts().size();
		int expected = 2;

		assertEquals("Expected 2 subscripts in 'literature'-script.", expected, actual);
	}

	@Test
	public void test_run_1st_subscript_name() throws CloneNotSupportedException {
		String text = TestFramework.getLiteratuteText();
		Script source = TestFramework.getLiteratureBlankScript();
		Script output = source.run(text);

		String actual = output.getScript(0).get("name").get("value");
		String expected = "Инки";

		assertEquals("Expected another 'name'-property value in 1st subscript.", expected, actual);
	}

	@Test
	public void test_run_2nd_subscript_name() throws CloneNotSupportedException {
		String text = TestFramework.getLiteratuteText();
		Script source = TestFramework.getLiteratureBlankScript();
		Script output = source.run(text);

		String actual = output.getScript(1).get("name").get("value");
		String expected = "Ассирия";

		assertEquals("Expected another 'name'-property value in 2nd subscript.", expected, actual);
	}

	@Test
	public void test_run_2nd_level_subscript_count() throws Exception {
		String text = TestFramework.getLiteratuteText();
		Script source = TestFramework.getLiteratureBlankScript();
		Script output = source.run(text);

		int actual = output.getScript(0).getScripts().size();
		int expected = 5;

		assertEquals("Expected another number of 2nd level subscripts.", expected, actual);
	}

	@Test
	public void test_getPattern() throws Exception {
		final String text = TestFramework.getLiteratuteText();
		final String from = "<td colspan=\"4\">";
		final String to = "<td colspan=\"4\">";

		Method method = Utils.class.getDeclaredMethod("getPattern", String.class, String.class, String.class);
		method.setAccessible(true);

		String actual = (String)method.invoke(null, text, from, to);
		String expected = "<h3>Инки</h3></td>\n<tr>\n<td> </td>\n";

		assertEquals("Expected another text fragment.", expected, actual);
	}

	@Test
	public void test_get_missing_attribute(){
		Script script = TestFramework.getScript();

		final String expected = "";
		final String actual = script.get("missing_attribute").get("value");

		assertEquals("Expected void value for missing attribute.", expected, actual);
	}
}