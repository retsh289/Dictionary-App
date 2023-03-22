package dao;

import java.util.HashMap;
import java.util.List;

import entity.Meaning;
import entity.Phonetic;
import entity.RelativeWord;
import entity.Vocabulary;

public interface VocabularyDAO extends DAO<Vocabulary> {
	List<Meaning> selectAllMeaningByVocabId(Integer vocabId);
	List<RelativeWord> selectAllRelativesByVocabId(Integer vocabId);
	List<Phonetic> selAllPhoneticByVocabId(Integer vocabId);
	List<Vocabulary> selectByPages(int pageNumber, int rowOfPages);
	Integer countNumberOfVocab();
	Integer insertGetId(Vocabulary vocab);
	List<Vocabulary> searchAll(String str);
	List<Vocabulary> sel5LastVocab();
	
	List<Vocabulary> selectIdAndWordAll();
}
