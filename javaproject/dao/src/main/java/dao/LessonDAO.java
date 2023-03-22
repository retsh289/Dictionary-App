package dao;

import java.util.List;

import entity.Lesson;
import entity.Vocabulary;

public interface LessonDAO extends DAO<Lesson>{
	Integer countNumberOfLesson();
	List<Lesson> selectByPages(int pageNumber, int rowOfPages);
	Integer insertGetId(Lesson ls);
	List<Lesson> searchAll(String str);

}
