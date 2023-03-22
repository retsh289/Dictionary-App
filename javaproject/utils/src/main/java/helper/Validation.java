package helper;

import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.JTextField;

public class Validation {
	public static boolean regexPatternOfvalidator(String regex, JTextField message, StringBuilder sb) {
		boolean ok = true;
		
		try {
			Pattern patt = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
			final Pattern matcher = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
			return patt.matcher(message.getText()).matches();
		} catch (Exception e) {
			sb.append("Vui Lòng Nhập Chính Xác Dữ Liệu").append("\n");
			e.printStackTrace();
			ok = false;
		}
		return ok;
	}
	
	public static boolean checkLength(String str, int minLength, int maxLength) {
		return (str.length() >= minLength && str.length() <= maxLength);
	}
	
	public static boolean checkRegex(String regex, String str) {
		return (Pattern.compile(regex).matcher(str).matches()) ? true : false;
	}
	
	public static void main(String[] args) {
		System.out.println(checkRegex(RegexPattern.EMAIL, "nguyenphu1147@gmail.com"));
	}
}
