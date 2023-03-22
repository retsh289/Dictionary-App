package dao;

import java.util.List;

import entity.Question;

public interface QuestionDAO extends DAO<Question>{
	List<Question> selAllQuestionByLessonId(Integer lessonId);
	Integer insertGetId(Question qs);
}
