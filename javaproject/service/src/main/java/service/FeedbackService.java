package service;

import dao.FeedbackDAO;
import dao.VocabularyContributionDAO;
import dao.impl.FeedbackDAOImpl;
import dao.impl.VocabularyContributionDAOImpl;
import entity.Feedback;
import entity.VocabularyContribution;

public class FeedbackService {
private FeedbackDAO fbDAO;
	
	public FeedbackService() {
		fbDAO = new FeedbackDAOImpl();
	}
	public Boolean delete(Feedback fb) {
		try {
			fbDAO.delete(fb);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
