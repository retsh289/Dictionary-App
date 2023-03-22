package dao;

import entity.UserLessonResult;

public interface UserLessonResultDAO extends DAO<UserLessonResult>{
	UserLessonResult find(Integer userId, Integer lessonId);
}
