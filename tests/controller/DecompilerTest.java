package controller;

import model.Config;
import org.junit.Test;

import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class DecompilerTest {

	@Test
	public void testRun(){
		Config.setProjectPath(TestFramework.getSourcePath());
		//Config.setProjectPath(Paths.get("c:\\projects\\Tabularium"));
		Decompiler decompiler = TestFramework.getDecompiler();

		String expected = TestFramework.getLiteratureScriptText();
		String actual = decompiler.run();

		assertEquals("Expected another decompilation result.", expected, actual);
	}

	@Test
	public void testRun_natural_attribute_sorting(){
		Config.setProjectPath(TestFramework.getSourcePath());
		Decompiler decompiler = TestFramework.getDecompiler();

		XmlParser parser = new XmlParser(decompiler.run());

		assertEquals("Expected 'output'-parameter will be 1st in script.", 0, parser.getScript().index("output"));
	}

	@Test
	public void testRun_number_attributes(){
		Config.setProjectPath(TestFramework.getSourcePath());
		Decompiler decompiler = TestFramework.getDecompiler();

		XmlParser parser = new XmlParser(decompiler.run());

		int expected = 6;
		int actual = parser.getScript().size();

		assertEquals("Expected another number of attributes in result file.", expected, actual);
	}

	@Test
	public void testRun_number_attribute_value_without_path_replaces() {
		Config.setProjectPath(TestFramework.getSourcePath());
		Decompiler decompiler = TestFramework.getDecompiler();

		XmlParser parser = new XmlParser(decompiler.run());

		String expected = "{$Templates}\\main.template";
		String actual = parser.getScript().get("MainTemplate").get("value");

		assertEquals("Expected special values s.a. {$BasePath} will not be replaced.", expected, actual);
	}
}