package controller;

import java.util.Collection;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
	public static String getPattern(final String text, final String from, final String to) {
		int start = text.indexOf(from) + from.length();
		if(start == -1){
			return "";
		}

		int end = text.indexOf(to, start);
		if(end == -1){
			end = text.length() - start;
		}

		return text.substring(start, end);
	}

	public static Collection<String> getPatterns(final String text, final String from, final String to) {
		Collection<String> result = new LinkedList();

		Pattern pattern = Pattern.compile(from + "(.*?)" + to, Pattern.DOTALL);
		Matcher matcher = pattern.matcher(text);

		while(matcher.find()){
			result.add(matcher.group());
		}

		return result;
	}
}
