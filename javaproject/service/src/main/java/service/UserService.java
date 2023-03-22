package service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;

import dao.impl.BookmarkDAOImpl;
import dao.impl.FeedbackDAOImpl;
import dao.impl.HistoryDAOImpl;
import dao.impl.UserDAOImpl;
import dao.impl.VocabularyContributionDAOImpl;
import entity.Bookmark;
import entity.User;
import helper.ErrorMessage;
import helper.RegexPattern;
import helper.Validation;

public class UserService {
	private UserDAOImpl dao;

	public UserService() {
		dao = new UserDAOImpl();
	}

	public boolean add(Map<String, String> data) {
		ErrorMessage.ERROR_MESSAGES = null;
		String email = data.get("email");
		String password = data.get("password");
		String confirmPassword = data.get("confirmPassword");
		Integer role = Integer.parseInt(data.get("role"));

		if(!validateUserInfo(email, password, confirmPassword)) return false;
		
		dao.insert(new User(email, password, role));
		return true;
	}

	private boolean validateUserInfo(String email, String password, String confirmPassword) {
		//	Validate (empty, format, password - confirmPassword, email existed, ...)
		if (email.equals("") || password.equals("") || confirmPassword.equals("")) {
			ErrorMessage.ERROR_MESSAGES = "Ô Email, Mật khẩu và Nhập lại mật khẩu không được để trống!";
			return false;
		} 
		
		if (new UserDAOImpl().checkExistEmail(email)) {
			ErrorMessage.ERROR_MESSAGES = "Email đã tồn tại";
			return false;
		} else if (!Validation.checkRegex(RegexPattern.EMAIL, email)) {
			ErrorMessage.ERROR_MESSAGES = "Định dạng Email không đúng!";
			return false;
		} else if (!Validation.checkLength(password, 5, 50)) {
			ErrorMessage.ERROR_MESSAGES = "Độ dài password ít nhất 5 ký tự và tối đa 50 ký tự";
			return false;
		} else if (!password.equals(confirmPassword)) {
			ErrorMessage.ERROR_MESSAGES = "Nhập lại mật khẩu không khớp!";
			return false;
		}
		return true;
	}

	public boolean update(Map<String, String> data) {
		ErrorMessage.ERROR_MESSAGES = null;
		Integer userId = Integer.parseInt(data.get("id"));
		Integer role = Integer.parseInt(data.get("role"));

		System.out.println(data);
		
		User originalUser = dao.select(userId);
		
		if(originalUser.getRoleId() == role) {
			ErrorMessage.ERROR_MESSAGES = "Bạn cần phải thay đổi thông tin để cập nhật !";
			return false;
		}
		
		User newUser = new User();
		newUser.setId(userId);
		newUser.setRoleId(role);
		dao.updateRole(newUser);
		
		return true;

	}


	public boolean delete(User user) {
//		delete history
		new HistoryDAOImpl().delByUserId(user.getId());
		Bookmark bm = new Bookmark();
		bm.getId();
//		delete bookmark
		new BookmarkDAOImpl().delByUserId(user.getId());
		new FeedbackDAOImpl().delByUserId(user.getId());
		new VocabularyContributionDAOImpl().delByUserId(user.getId());
		//		delete user	
		return dao.delete(user) == 1 ? true : false;
	}
}
