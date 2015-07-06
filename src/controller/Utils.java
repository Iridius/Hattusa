package controller;

import java.util.Collection;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
	public static String getPattern(final String text, final String from, final String to) {
		int start = text.indexOf(from);
		if(start == -1 && text.indexOf(to, 0) == -1){
			return "";
		}

		start = start == -1 ? 0: start + from.length();

		int end = text.indexOf(to, start);
		end = end == -1 ? text.length() : end;

		return text.substring(start, end);
	}

	public static Collection<String> getPatterns(final String text, final String from, final String to) {
		Collection<String> result = new LinkedList();

		Pattern pattern = Pattern.compile(from + "(.*?)" + to, Pattern.DOTALL);
		Matcher matcher = pattern.matcher(text);

		while(matcher.find()){
			result.add(matcher.group(1));
		}

		return result;
	}
}
