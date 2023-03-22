package dao;

import java.util.List;

import entity.Bookmark;
import entity.History;
import entity.User;
import entity.Vocabulary;

public interface UserDAO extends DAO<User> {
	List<User> getList(int level);
	Integer updatePassword(User user);
	Integer updateRole(User user);

	boolean checkExistEmail(String email);
	
	History selectHistoryByUserId(Integer userId);
	Bookmark selectBookmarkByUserId(Integer userId);
	
	List<User> selectUserByPages(int pageNumber, int rowOfPages);
	Integer countNumberOfUser();
	
	List<User> selectAdminByPages(int pageNumber, int rowOfPages);
	Integer countNumberOfAdmin();
	Integer selectIdByUserEmail(String email);
	List<User> searchAll(String str, Integer roleId);

}
