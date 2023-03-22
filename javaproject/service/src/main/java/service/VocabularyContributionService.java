package service;

import dao.VocabularyContributionDAO;
import dao.impl.VocabularyContributionDAOImpl;
import entity.VocabularyContribution;

public class VocabularyContributionService {
	private VocabularyContributionDAO vcDAO;
	
	public VocabularyContributionService() {
		vcDAO = new VocabularyContributionDAOImpl();
	}
	public Boolean delete(VocabularyContribution vc) {
		try {
			vcDAO.delete(vc);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
