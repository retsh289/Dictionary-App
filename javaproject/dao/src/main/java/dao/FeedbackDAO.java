package dao;

import java.util.List;

import entity.Answer;
import entity.Feedback;
import entity.VocabularyContribution;

public interface FeedbackDAO extends DAO<Feedback>{
	Integer countFeedback();
	List<Feedback> selectByPages(int pageNumber, int rowOfPages);
}
