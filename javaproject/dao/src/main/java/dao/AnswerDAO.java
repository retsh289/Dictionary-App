package dao;

import java.util.List;

import entity.Answer;

public interface AnswerDAO extends DAO<Answer>{
	List<Answer> selAllAnswerByQuestionId(Integer questionId);
}
