package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import database.ConnectDBFromProperties;
import entity.Example;
import entity.Meaning;
import dao.MeaningDAO;

public class MeaningDAOImpl extends AbstractDAO<Meaning>  implements MeaningDAO {
	@Override
	/**
	 * @return null if it doesn't exist
	 */
	public Meaning select(Integer id) {
		Meaning meaning = null;
		try(
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call selMeaning(?)}");
		){
			cs.setInt(1, id);
			var rs = cs.executeQuery();
			
			if(rs.next()) {
				Integer meaningId = rs.getInt(1);
				String content = rs.getString(2);
				Integer vocabId = rs.getInt(3);
				meaning = new Meaning(meaningId, content, vocabId);
			}
		} catch(Exception e) {
//			e.printStackTrace();
			System.err.println("Select A Meaning Failed!");
		}
		return meaning;
	}
	
	
	@Override
	public List<Meaning> selectAll() {
		List<Meaning> list = new ArrayList<>();
		try(
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call selAllMeaning}");
			var rs = cs.executeQuery();
		){
			while(rs.next()) {
				Integer meaningId = rs.getInt(1);
				String content = rs.getString(2);
				Integer vocabId = rs.getInt(3);
				list.add(new Meaning(meaningId, content, vocabId));
			}
		} catch(Exception e) {
//			e.printStackTrace();
			System.err.println("Select all Meaning failed!");
		}
		return list;
	}

	@Override
	/**
	 * @return 0 for insert failed
	 * @return 1 for insert successfully
	 */
	public Integer insert(Meaning meaning) {
		Integer result = 0;
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call insertMeaning(?, ?)}")
		){
			cs.setString(1, meaning.getContent());
			cs.setInt(2, meaning.getVocabularyId());
			result = cs.executeUpdate();
		} catch (Exception e) {
//			e.printStackTrace();
			System.err.println("Insert Meaning failed!");
		}
		return result;
	}

	@Override
	/**
	 * @return 0 for update failed
	 * @return 1 for update successfully
	 */
	public Integer update(Meaning meaning) {
		Integer result = 0;
		try(
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call updateMeaning(?, ?, ?)}");
		){
			cs.setInt(1, meaning.getId());
			cs.setString(2, meaning.getContent());
			cs.setInt(3, meaning.getVocabularyId());
			result = cs.executeUpdate();
		} catch(Exception e) {
//			e.printStackTrace();
			System.err.println("Update Meaning failed");
		}
		return result;
	}

	@Override
	/**
	 * @return 0 for delete failed
	 * @return 1 for delete successfully
	 */
	public Integer delete(Meaning meaning) {
		Integer result = 0;
		try(
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call deleteMeaning(?)}");
		){
			cs.setInt(1, meaning.getId());
			result = cs.executeUpdate();
		} catch(Exception e) {
//			e.printStackTrace();
			System.err.println("Delete a Meaning failed");
		}
		return result;
	}

	
	
	@Override

	public List<Example> selectAllExampleByMeaningId(Integer meaningId) {
		List<Example> list = new ArrayList<>();
		try(
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call selExamplesByMeaningId(?)}");
		){
			cs.setInt(1, meaningId);
			var rs = cs.executeQuery();
			while(rs.next()) {
				Integer exId = rs.getInt(1);
				String content = rs.getString(2);
				String meaning = rs.getString(3);
				Integer meaningIdRs = rs.getInt(4);
				list.add(new Example(exId, content, meaning, meaningIdRs));
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.err.println("selectAllExampleByMeaningId failed!");
		}
		return list;
	}

	@Override
	public Integer insertGetId(Meaning meaning) {
		String sql = "INSERT INTO MEANING VALUES (?, ?)";
		int result = -1;
		try(
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			PreparedStatement  ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		){
			ps.setString(1, meaning.getContent());
			ps.setInt(2, meaning.getVocabularyId());
			
			int affectedRows = ps.executeUpdate();

	        if (affectedRows == 0) {
	            throw new SQLException("Creating user failed, no rows affected.");
	        }

	        try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	result =  generatedKeys.getInt(1);
	            }
	            else {
	                throw new SQLException("Insert get id meaning failed, no ID obtained.");
	            }
	        }
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Insert get id meaning failed");
		}
		return result;
	}

}
