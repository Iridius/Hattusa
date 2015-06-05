package controller;

import model.Script;
import org.junit.Test;

import static org.junit.Assert.*;

public class DecompilerTest {

	@Test
	public void testRun(){
		String text = TestFramework.getLiteratuteText();
		Script script = TestFramework.getLiteratureScript();
		Decompiler decompiler = new Decompiler(text, script);

		String expected = "";
		String actual = decompiler.run();

		assertEquals("Expected another decompilation result/", expected, actual);
	}
}