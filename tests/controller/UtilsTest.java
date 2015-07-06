package controller;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;

public class UtilsTest {

	@Test
	public void testGetPattern() {
        final String pattern = "<strong>Боден Л.</strong>";
		final String from = "<strong>";
		final String to = "</strong>";

		final String expected = "Боден Л.";
		final String actual = Utils.getPattern(pattern, from , to);

		assertEquals("Expected another extracted value.", expected, actual);
	}

	@Test
	public void testGetPattern_without_start_tag() {
		final String pattern = "Боден Л.</strong>";
		final String from = "<strong>";
		final String to = "</strong>";

		final String expected = "Боден Л.";
		final String actual = Utils.getPattern(pattern, from , to);

		assertEquals("Expected extracted value although start tag is missing.", expected, actual);
	}

	@Test
	public void testGetPatterns() throws Exception {
		final String pattern = "<strong>Боден Л.</strong><strong>Зубрицкий Ю. А.</strong><strong>Кенделл Э.</strong>";
		final String from = "<strong>";
		final String to = "</strong>";

		final Collection<String> result = Utils.getPatterns(pattern, from , to);
		assertEquals("Expected 3 records in result.", 3, result.size());
		assertTrue("Expected result contains 'Боден Л.'.", result.contains("Боден Л."));
		assertTrue("Expected result contains 'Зубрицкий Ю. А.'.", result.contains("Зубрицкий Ю. А."));
		assertTrue("Expected result contains 'Кенделл Э.'.", result.contains("Кенделл Э."));
	}
}