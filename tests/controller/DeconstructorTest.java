package controller;

import org.junit.Test;

import static org.junit.Assert.*;

public class DeconstructorTest {

	@Test
	public void testRun(){
		String text = TestFramework.getLiteratureScript();
		Deconstructor deconstructor = new Deconstructor(text, null);

		String result = deconstructor.run();
	}
}