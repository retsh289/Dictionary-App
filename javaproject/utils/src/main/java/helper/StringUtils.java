package helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringUtils {
	public static String toCapitalize(String str) {
		if(str.length() <= 0) return str;
		str.toLowerCase();
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
	
	public static String fileNameFormat(String str) {
		return str.trim().replaceAll("\\s+", "_").toLowerCase();
	}
	
	public static String formatWord(String str) {
		return str.trim().replaceAll("\\s+", "_").toLowerCase();
	}
	
	public static String formatVocab(String str) {
		return str.trim().toLowerCase();
	}
	
	public static String toCamelCase(String text) {
		List<String> words = Arrays.asList(text.split("\\s+"));
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < words.size(); i++) {
			String word = words.get(i);
		    builder.append(word.isEmpty() ? word : StringUtils.toCapitalize(word));
		    if(i != words.size() - 1) builder.append(" ");
		}
		return builder.toString();
	}
	
}


