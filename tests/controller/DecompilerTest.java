package controller;

import model.Config;
import model.Script;
import org.junit.Test;

import static org.junit.Assert.*;

public class DecompilerTest {

	@Test
	public void testRun(){
		Config.setProjectPath(TestFramework.getSourcePath());
		Decompiler decompiler = TestFramework.getDecompiler();

		String expected = "";
		String actual = decompiler.run();

		assertEquals("Expected another decompilation result.", expected, actual);
	}

	@Test
	public void testRun_number_attributes(){
		Config.setProjectPath(TestFramework.getSourcePath());
		Decompiler decompiler = TestFramework.getDecompiler();

		XmlParser parser = new XmlParser(decompiler.run());

		int expected = 5;
		int actual = parser.getContent().size();

		assertEquals("Expected another number of attributes in result file.", expected, actual);
	}

	@Test
	public void testRun_number_attribute_value_without_path_replaces() {
		Config.setProjectPath(TestFramework.getSourcePath());
		Decompiler decompiler = TestFramework.getDecompiler();

		XmlParser parser = new XmlParser(decompiler.run());

		String expected = "{$Templates}\\main.template";
		String actual = parser.getContent().get("MainTemplate");

		assertEquals("Expected special values s.a. {$BasePath} will not be replaced.", expected, actual);
	}
}