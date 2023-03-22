package dao;

import java.util.List;

import entity.Theory;

public interface TheoryDAO extends DAO<Theory>{
	List<Theory> selAllTheoriesByLessonId(Integer lessonId);
	Integer deleteByVocabId(Integer vocabId);
}
