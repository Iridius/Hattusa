package controller;

import model.Config;
import model.Script;
import org.junit.Test;

import static org.junit.Assert.*;

public class DecompilerTest {

	@Test
	public void testRun(){
		String text = TestFramework.getLiteratuteText();
		Script script = TestFramework.getLiteratureScript();

		Config.setProjectPath(TestFramework.getSourcePath());
		Decompiler decompiler = new Decompiler(text, script);

		String expected = "";
		String actual = decompiler.run();

		assertEquals("Expected another decompilation result.", expected, actual);
	}

	@Test
	public void testRun_number_attributes(){
		String text = TestFramework.getLiteratuteText();
		Script script = TestFramework.getLiteratureScript();

		Config.setProjectPath(TestFramework.getSourcePath());
		Decompiler decompiler = new Decompiler(text, script);

		XmlParser parser = new XmlParser(decompiler.run());

		int expected = 5;
		int actual = parser.getContent().size();

		assertEquals("Expected another number of attributes in result file.", expected, actual);
	}
}