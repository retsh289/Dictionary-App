package dao.impl;

import java.util.ArrayList;
import java.util.List;

import dao.WordTypeDAO;
import database.ConnectDBFromProperties;
import entity.WordType;

public class WordTypeDAOImpl extends AbstractDAO<WordType>  implements WordTypeDAO{

	@Override
	/**
	 * @return null if don't have any
	 */
	public List<WordType> selectAll() {
		List<WordType> list = new ArrayList<>();
		try(
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call selAllWordType}");
			var rs = cs.executeQuery();
		){
			while(rs.next()) {
				var type = new WordType();
				type.setType(rs.getString("type"));
				list.add(type);
			}
		} catch(Exception e) {
//			e.printStackTrace();
			System.out.println("Select all WordType failed");
		}
		return list;
	}

	@Override
	public WordType select(Integer id) {
		WordType wt = null;
		try(
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call selWordType(?)}");
		){
			cs.setInt(1, id);
			var rs = cs.executeQuery();
			if(rs.next()) {
				wt = new WordType(rs.getString(2));
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Select WordType failed");
		}
		return wt;
	}
	
	public String get(Integer typeId) {
		String result = null;
		switch (typeId) {
		case 1: {
			result = "NOUN";
		}
		case 2: {
			result = "PRONOUN";
		}
		case 3: {
			result = "ADJECTIVE";
		}
		case 4: {
			result = "VERB";
		}
		case 5: {
			result = "ADVERB";
		}
		case 6: {
			result = "DETERMINER";
		}
		case 7: {
			result = "PREPOSITION";
		}
		case 8: {
			result = "CONJUNTION";
		}
		case 9: {
			result = "INTERJECTION";
		}
		default: {
			result = "NOUN";
		}
		}
		return result;
		
	}

	@Override
	@Deprecated
	public Integer insert(WordType t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Deprecated
	public Integer update(WordType t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Deprecated
	public Integer delete(WordType t) {
		// TODO Auto-generated method stub
		return null;
	}
}