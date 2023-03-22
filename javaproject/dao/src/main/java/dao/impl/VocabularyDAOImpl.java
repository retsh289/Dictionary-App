package dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import database.CallableStatementUtils;
import database.ConnectDBFromProperties;
import entity.Meaning;
import entity.Phonetic;
import entity.RelativeWord;
import entity.Vocabulary;
import dao.VocabularyDAO;

public class VocabularyDAOImpl extends AbstractDAO<Vocabulary> implements VocabularyDAO {
	@Override
	/**
	 * @return null if it doesn't exist
	 */
	public Vocabulary select(Integer id) {
		Vocabulary vocab = null;
		try(
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call selVocab(?)}");
		){
			cs.setInt(1, id);
			var rs = cs.executeQuery();
			
			if(rs.next()) {
				Integer vocab_id = rs.getInt(1);
				String word = rs.getString(2);
				String image = rs.getString(3);
				String pronunciation = rs.getString(4);
				Integer categoryId = rs.getInt(5);
				Integer wordTypeId = rs.getInt(6);
				vocab = new Vocabulary(vocab_id, word, image, pronunciation, categoryId, wordTypeId);
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.err.println("Select A Vocabulary Failed!");
		}
		return vocab;
	}

	@Override
	public List<Vocabulary> selectAll() {
		List<Vocabulary> list = new ArrayList<>();
		try(
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call selAllVocab}");
			var rs = cs.executeQuery();
		){
			while(rs.next()) {
				Integer vocab_id = rs.getInt(1);
				String word = rs.getString(2);
				String image = rs.getString(3);
				String pronunciation = rs.getString(4);
				Integer categoryId = rs.getInt(5);
				Integer wordTypeId = rs.getInt(6);
				
				list.add(
					new Vocabulary(vocab_id, word, image, pronunciation, categoryId, wordTypeId));
			}
		} catch(Exception e) {
//			e.printStackTrace();
			System.err.println("Select all vocabulary failed!");
		}
		return list;
	}

	@Override
	/**
	 * @return 0 for insert failed
	 * @return 1 for insert successfully
	 */
	public Integer insert(Vocabulary vocab) {
		Integer result = 0;
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call insertVocab(?, ?, ?, ?, ?, ?)}")
		){
			cs.setString(1, vocab.getWord());
			if(vocab.getImage() != null) {
				cs.setString(2, vocab.getImage());
			} else {
				cs.setNull(2, Types.NVARCHAR);
			}
			if(vocab.getPronunciation() != null) {
				cs.setString(3, vocab.getPronunciation());
			} else {
				cs.setNull(3, Types.NVARCHAR);
			}
			if(vocab.getCategoryId() != null) {
				cs.setInt(4, vocab.getCategoryId());
			} else {
				cs.setNull(4, Types.INTEGER);
			}
			if(vocab.getWordTypeId() != null) {
				cs.setInt(5, vocab.getWordTypeId());
			} else {
				cs.setNull(5, Types.INTEGER);
			}
			result = cs.executeUpdate();
		} catch (Exception e) {
//			e.printStackTrace();
			System.err.println("Insert vocabulary failed!");
		}
		return result;
	}
	
	@Override
	/**
	 * @return 0 for update failed
	 * @return 1 for update successfully
	 */
	public Integer update(Vocabulary vocab) {
		Integer result = 0;
		try(
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call updateVocab(?, ?, ?, ?, ?, ?)}");
		){
			cs.setInt(1, vocab.getId());
			cs.setString(2, vocab.getWord());
			if(vocab.getImage() != null) {
				cs.setString(3, vocab.getImage());				
			} else {
				cs.setNull(3, Types.NVARCHAR);
			}
			if(vocab.getPronunciation() != null) {
				cs.setString(4, vocab.getPronunciation());				
			} else {
				cs.setNull(4, Types.NVARCHAR);
			}
			if(vocab.getCategoryId() != null) {
				cs.setInt(5, vocab.getCategoryId());
			} else {
				cs.setNull(5, Types.INTEGER);
			}
			if(vocab.getWordTypeId() != null) {
				cs.setInt(6, vocab.getWordTypeId());
			} else {
				cs.setNull(6, Types.INTEGER);
			}
			result = cs.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Update vocab failed");
		}
		return result;
	}

	@Override
	/**
	 * @return 0 for delete failed
	 * @return 1 for delete successfully
	 */
	public Integer delete(Vocabulary vocab) {
		Integer result = 0;
		try(
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call deleteVocab(?)}");
		){
			cs.setInt(1, vocab.getId());
			result = cs.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
			System.err.println("Delete a vocabulary failed");
		}
		return result;
	}
	
	@Override
	public List<Meaning> selectAllMeaningByVocabId(Integer vocabId) {
		List<Meaning> list = new ArrayList<>();
		try(
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call selMeaningsByVocabId(?)}");
		){
			cs.setInt(1, vocabId);
			var rs = cs.executeQuery();
			while(rs.next()) {
				Integer meaningId = rs.getInt(1);
				String content = rs.getString(2);
				Integer vocabIdRs = rs.getInt(3);
				list.add(new Meaning(meaningId, content, vocabIdRs));
			}
		} catch(Exception e) {
//			e.printStackTrace();
			System.err.println("Select all Meaning By Vocabulary Id failed!");
		}
		return list;
	}


	@Override
	public List<RelativeWord> selectAllRelativesByVocabId(Integer vocabId) {
		List<RelativeWord> list = new ArrayList<>();
		try(
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call selRelativesByVocabId(?)}");
		){
			cs.setInt(1, vocabId);
			var rs = cs.executeQuery();
			while(rs.next()) {
				Integer relId = rs.getInt(1);
				String word = rs.getString(2);
				Integer vocabIdRs = rs.getInt(3);
				list.add(new RelativeWord(relId, word, vocabIdRs));
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.err.println("Select all Relatives By Vocabulary Id failed!");
		}
		return list;
	}
	
	@Override
	public List<Phonetic> selAllPhoneticByVocabId(Integer vocabId) {
		List<Phonetic> list = new ArrayList<>();
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call selPhoneticByVocabId(?)}");
		) {
			cs.setInt(1, vocabId);
			var rs = cs.executeQuery();
			while (rs.next()) {
				Integer pntId = rs.getInt(1);
				String content = rs.getString(2);
				Integer vocabIdRs = rs.getInt(3);
				list.add(new Phonetic(pntId, content, vocabIdRs));
			}
		} catch (Exception e) {
			// e.printStackTrace();
			System.err.println("Select all Phonetics by Vocab Id failed!");
		}
		return list;
	}
	
	@Override
	public List<Vocabulary> selectByPages(int pageNumber, int rowOfPages) {
		List<Vocabulary> list = new ArrayList<>();
		
		try(
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = CallableStatementUtils.createCS(con, "{call selVocabByPages(?, ?)}", pageNumber, rowOfPages);
			var rs = cs.executeQuery();
		){
			while(rs.next()) {
				Integer vocab_id = rs.getInt(1);
				String word = rs.getString(2);
				String image = rs.getString(3);
				String pronunciation = rs.getString(4);
				Integer categoryId = rs.getInt(5);
				Integer wordTypeId = rs.getInt(6);
				list.add(
					new Vocabulary(vocab_id, word, image, pronunciation, categoryId, wordTypeId));
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Select Vocab By Pages Failed");
		}
		return list;
	}

	@Override
	public Integer countNumberOfVocab() {
		int count = 0;
		
		try(
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call countVocab}");
			var rs = cs.executeQuery();
		){
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return count;
	}

	@Override
	/**
	 * @return -1 if failed
	 */
	public Integer insertGetId(Vocabulary vocab) {
		String sql = "INSERT INTO VOCABUlARY VALUES (?, ?, ?, ?, ?)";
		int result = -1;
		try(
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			PreparedStatement  ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		){
			ps.setString(1, vocab.getWord());
			if(vocab.getImage() != null) {
				ps.setString(2, vocab.getImage());
			} else {
				ps.setNull(2, Types.NVARCHAR);
			}
			if(vocab.getPronunciation() != null) {
				ps.setString(3, vocab.getPronunciation());
			} else {
				ps.setNull(3, Types.NVARCHAR);
			}
			if(vocab.getCategoryId() != null) {
				ps.setInt(4, vocab.getCategoryId());
			} else {
				ps.setNull(4, Types.INTEGER);
			}
			if(vocab.getWordTypeId() != null) {
				ps.setInt(5, vocab.getWordTypeId());
			} else {
				ps.setNull(5, Types.INTEGER);
			}
			
			int affectedRows = ps.executeUpdate();

	        if (affectedRows == 0) {
	            throw new SQLException("Creating user failed, no rows affected.");
	        }

	        try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	result =  generatedKeys.getInt(1);
	            }
	            else {
	                throw new SQLException("Insert get id vocab failed, no ID obtained.");
	            }
	        }
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Insert get id vocab failed");
		}
		return result;
	}
	
	@Override
	public List<Vocabulary> searchAll(String str) {
		List<Vocabulary> list = new ArrayList<>();
		try(
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call searchAllVocab(?)}");
		){
			cs.setString(1,str );
			var rs = cs.executeQuery();
			while(rs.next()) {
				Integer vocab_id = rs.getInt(1);
				String word = rs.getString(2);
				String image = rs.getString(3);
				String pronunciation = rs.getString(4);
				Integer categoryId = rs.getInt(5);
				Integer wordTypeId = rs.getInt(6);
				
				list.add(
					new Vocabulary(vocab_id, word, image, pronunciation, categoryId, wordTypeId));
			}
		} catch(Exception e) {
//			e.printStackTrace();
			System.err.println("search all vocabulary failed!");
		}
		return list;
	}

	@Override
	public List<Vocabulary> sel5LastVocab() {
		List<Vocabulary> list = new ArrayList<>();
		try(
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call sel5LastVocab}");
			var rs = cs.executeQuery();
		){
			while(rs.next()) {
				Integer vocab_id = rs.getInt(1);
				String word = rs.getString(2);
				String image = rs.getString(3);
				String pronunciation = rs.getString(4);
				Integer categoryId = rs.getInt(5);
				Integer wordTypeId = rs.getInt(6);
				
				list.add(
					new Vocabulary(vocab_id, word, image, pronunciation, categoryId, wordTypeId));
			}
		} catch(Exception e) {
//			e.printStackTrace();
			System.err.println("Select top 5 last vocabulary failed!");
		}
		return list;
	}

	@Override
	public List<Vocabulary> selectIdAndWordAll() {
		List<Vocabulary> list = new ArrayList<>();
		try(
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call selIdAndWordAllVocab}");
			var rs = cs.executeQuery();
		){
			while(rs.next()) {
				Integer vocab_id = rs.getInt(1);
				String word = rs.getString(2);
				Vocabulary vocab = new Vocabulary();
				vocab.setId(vocab_id);
				vocab.setWord(word);
				list.add(vocab);
			}
		} catch(Exception e) {
//			e.printStackTrace();
			System.err.println("Select Id and Word all vocabulary failed!");
		}
		return list;
	}
	
	
}
