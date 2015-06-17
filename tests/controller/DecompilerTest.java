package controller;

import model.Config;
import model.FileData;
import model.Script;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DecompilerTest {

	@Test
	public void testRun(){
		Config.setProjectPath(TestFramework.getSourcePath());
		Decompiler decompiler = TestFramework.getDecompiler();

		String expected = TestFramework.getLiteratureScriptText();
		String actual = decompiler.run().getFirst();

		assertEquals("Expected another decompilation result.", expected, actual);
	}

	@Test
	public void testRun_natural_attribute_sorting(){
		Config.setProjectPath(TestFramework.getSourcePath());
		Decompiler decompiler = TestFramework.getDecompiler();

		XmlParser parser = new XmlParser(decompiler.run().getFirst());

		assertEquals("Expected 'output'-parameter will be 1st in script.", 0, parser.getScript().index("output"));
	}

	@Test
	public void testRun_number_attributes(){
		Config.setProjectPath(TestFramework.getSourcePath());
		Decompiler decompiler = TestFramework.getDecompiler();

		XmlParser parser = new XmlParser(decompiler.run().getFirst());

		int expected = 6;
		int actual = parser.getScript().size();

		assertEquals("Expected another number of attributes in result file.", expected, actual);
	}

	@Test
	public void testRun_number_attribute_value_without_path_replaces() {
		Config.setProjectPath(TestFramework.getSourcePath());
		Decompiler decompiler = TestFramework.getDecompiler();

		XmlParser parser = new XmlParser(decompiler.run().getFirst());

		String expected = "{$Templates}\\main.template";
		String actual = parser.getScript().get("MainTemplate").get("value");

		assertEquals("Expected special values s.a. {$BasePath} will not be replaced.", expected, actual);
	}

	@Test
	public void testRun_simple_attributes(){
		Config.setProjectPath(TestFramework.getSourcePath());
		Decompiler decompiler = TestFramework.getDecompiler(TestFramework.getBookScriptText());

		FileData data = decompiler.run();
		assertTrue(true);
	}
}