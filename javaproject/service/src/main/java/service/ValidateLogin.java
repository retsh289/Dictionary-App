package service;

import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;

import helper.RegexPattern;

public class ValidateLogin {
	public static boolean  checkEmail;
	public static boolean  checkPassword;
	public static boolean checkAll(JTextField textFieldEmail,JTextField textFieldPassword,StringBuilder s) {
		checkEmail=Login.checkRegexLogin(RegexPattern.EMAIL, textFieldEmail, s,"email");
		checkPassword= Login.checkRegexLogin(RegexPattern.PASSWORD, textFieldPassword, s,"Password");
		boolean ok = true;
		if (!(checkPassword && checkEmail)) {
			return false;
		}
		return ok;
	}
}
