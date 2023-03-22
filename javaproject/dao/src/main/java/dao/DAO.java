package dao;

import java.util.List;

import entity.Vocabulary;

public interface DAO<T> {
	T select(Integer id);
	List<T> selectAll();
	
	/**
	 * @return 0 for insert failed
	 * @return 1 for insert successfully
	 */
	Integer insert(T t);
	
	/**
	 * @return 0 for update failed
	 * @return 1 for update successfully
	 */
	Integer update(T t);
	
	/**
	 * @return 0 for delete failed
	 * @return 1 for delete successfully
	 */
	Integer delete(T t);
	


	
}
