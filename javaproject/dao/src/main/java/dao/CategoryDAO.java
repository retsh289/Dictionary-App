package dao;

import java.util.List;

import entity.Category;
import entity.Vocabulary;

public interface CategoryDAO extends DAO<Category> {
	List<Vocabulary> selectAllVocabByCategoryId(Integer cateId);
	List<Category> selectByPages(int pageNumber, int rowOfPages);
	List<Category> selTop5();
	Integer countNumberOfCate();
	
	Integer getIdFromCateName(String name);
	List<Category> searchAll(String str);
}
