package dao;

import java.util.List;

import entity.VocabularyContribution;

public interface VocabularyContributionDAO extends DAO<VocabularyContribution>{
	Integer countVocabContri();
	List<VocabularyContribution> selectByPages(int pageNumber, int rowOfPages);
}
