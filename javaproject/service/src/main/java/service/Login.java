package service;

import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import helper.RegexPattern;

public class Login {

	public static boolean isEmpty(JTextField field, StringBuilder sb, String msg) {
		boolean ok = true;
		if (field.getText().equals("")) {
			sb.append(msg).append("\n");
			ok = false;
		} else {
			ok = true;
		}
		return ok;
	}

	public static boolean checkRegexLogin(String regex, JTextField message, StringBuilder sb, String fieldComponent) {
		boolean ok = true;

		try {
			isEmpty(message, sb, "vui lòng nhập dữ liệu " + fieldComponent);
			Pattern patt = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
			final Pattern matcher = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
			return patt.matcher(message.getText()).matches();
		} catch (Exception e) {
			sb.append("Vui Lòng Nhập dữ liệu" + fieldComponent).append("\n");
			e.printStackTrace();
			ok = false;
		}
		return ok;
	}

	public static void checkColorText(String regex, JTextField message, StringBuilder sb, String fieldComponent) {
		if (checkRegexLogin(regex, message, sb, fieldComponent)) {
		} else {
		}
	}

}
