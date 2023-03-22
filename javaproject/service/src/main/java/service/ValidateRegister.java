package service;

import java.awt.Color;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.mindrot.jbcrypt.BCrypt;

import dao.impl.UserDAOImpl;
import entity.User;
import helper.RegexPattern;

public class ValidateRegister {
	public static boolean checkAll(JTextField textFieldEmail,JTextField textFieldPassword,JTextField textFieldPasswordConfirm,StringBuilder s) {
		boolean ok = true;
		boolean checkEmail=  Register.checkRegexRegister(RegexPattern.EMAIL, textFieldEmail,s, "email");
		boolean checkPassword= Register.checkRegexRegister(RegexPattern.PASSWORD, textFieldPassword, s,"password");
		boolean checkPasswordConfirm= Register.checkPasswordConfirm( textFieldPassword,textFieldPasswordConfirm, s);
		if (!(checkPassword &&checkEmail&&checkPasswordConfirm)) {
			return false;
		}
		return ok;
	}
}
