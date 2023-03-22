package dao;

import java.util.List;

import entity.Example;
import entity.Meaning;

public interface MeaningDAO extends DAO<Meaning> {
	List<Example> selectAllExampleByMeaningId(Integer meaningId);
	Integer insertGetId(Meaning meaning);
}
