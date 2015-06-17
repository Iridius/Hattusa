package controller;

import model.Config;
import model.FileData;
import model.Script;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DecompilerTest {

	@Test
	public void testRun(){
		Config.setProjectPath(TestFramework.getSourcePath());
		String text = TestFramework.getLiteratuteText();
		Script script = TestFramework.getLiteratureScript();

		String expected = TestFramework.getLiteratureScriptText();
		String actual = Decompiler.run(text, script).getFirst();

		assertEquals("Expected another decompilation result.", expected, actual);
	}

	@Test
	public void testRun_natural_attribute_sorting(){
		Config.setProjectPath(TestFramework.getSourcePath());

		String text = TestFramework.getLiteratuteText();
		Script script = TestFramework.getLiteratureScript();
		XmlParser parser = new XmlParser(Decompiler.run(text, script).getFirst());

		assertEquals("Expected 'output'-parameter will be 1st in script.", 0, parser.getScript().index("output"));
	}

	@Test
	public void testRun_number_attributes(){
		Config.setProjectPath(TestFramework.getSourcePath());

		String text = TestFramework.getLiteratuteText();
		Script script = TestFramework.getLiteratureScript();
		XmlParser parser = new XmlParser(Decompiler.run(text, script).getFirst());

		int expected = 5;
		int actual = parser.getScript().size();

		assertEquals("Expected another number of attributes in result file.", expected, actual);
	}

	@Test
	public void testRun_number_attribute_value_without_path_replaces() {
		Config.setProjectPath(TestFramework.getSourcePath());

		String text = TestFramework.getLiteratuteText();
		Script script = TestFramework.getLiteratureScript();
		XmlParser parser = new XmlParser(Decompiler.run(text, script).getFirst());

		String expected = "{$Templates}\\main.template";
		String actual = parser.getScript().get("MainTemplate").get("value");

		assertEquals("Expected special values s.a. {$BasePath} will not be replaced.", expected, actual);
	}

	@Test
	public void testRun_simple_attributes(){
		Config.setProjectPath(TestFramework.getSourcePath());
		String text = TestFramework.getBookScriptText();
		Script script = TestFramework.getLiteratureScript();

		String content = Decompiler.run(text, script).getFirst();
		assertTrue("incomplete test.", false);
	}

	@Test
	public void test_Run_get_all_tree_results(){
		Config.setProjectPath(TestFramework.getSourcePath());

		String text = TestFramework.getLiteratuteText();
		Script script = TestFramework.getLiteratureScript();
		FileData data = Decompiler.run(text, script);

		assertTrue("Expected compiled data will contains main script path.", data.getKeys().contains("{$BasePath}\\literature.thtml"));
		assertTrue("Expected compiled data will contains second-level script path.", data.getKeys().contains("{$BasePath}\\Literature\\Ассирия.thtml"));
		assertTrue("Expected compiled data will contains third-level script path.", data.getKeys().contains("{$BasePath}\\Literature\\Ассирия\\Ассирия_1.thtml"));
	}
}