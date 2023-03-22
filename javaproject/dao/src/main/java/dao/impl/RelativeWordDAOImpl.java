package dao.impl;

import java.util.ArrayList;
import java.util.List;

import database.ConnectDBFromProperties;
import entity.RelativeWord;
import dao.RelativeWordDAO;

public class RelativeWordDAOImpl extends AbstractDAO<RelativeWord>  implements RelativeWordDAO {
	@Override
	public RelativeWord select(Integer id) {
		RelativeWord rel = null;
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call selRel(?)}");
		) {
			cs.setInt(1, id);
			var rs = cs.executeQuery();

			if (rs.next()) {
				Integer relId = rs.getInt(1);
				String word = rs.getString(2);
				Integer vocabId = rs.getInt(3);
				rel = new RelativeWord(relId, word, vocabId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Select A Relatives Failed!");
		}
		return rel;
	}

	@Override
	public List<RelativeWord> selectAll() {
		List<RelativeWord> list = new ArrayList<>();
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call selAllRel}");
			var rs = cs.executeQuery();
		) {
			while (rs.next()) {
				Integer relId = rs.getInt(1);
				String word = rs.getString(2);
				Integer vocabId = rs.getInt(3);
				list.add(new RelativeWord(relId, word, vocabId));
			}
		} catch (Exception e) {
//			e.printStackTrace();
			System.err.println("Select all Relatives failed!");
		}
		return list;
	}

	@Override
	/**
	 * @return 0 for insert failed
	 * @return 1 for insert successfully
	 */
	public Integer insert(RelativeWord rel) {
		Integer result = 0;
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call insertRel(?, ?)}")
		) {
			cs.setString(1, rel.getWord());
			cs.setInt(2, rel.getVocabularyId());
			result = cs.executeUpdate();
		} catch (Exception e) {
//			e.printStackTrace();
			System.err.println("Insert Relatives failed!");
		}
		return result;
	}

	@Override
	/**
	 * @return 0 for update failed
	 * @return 1 for update successfully
	 */
	public Integer update(RelativeWord rel) {
		Integer result = 0;
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call updateRel(?, ?, ?)}");
		) {
			cs.setInt(1, rel.getId());
			cs.setString(2, rel.getWord());
			cs.setInt(3, rel.getVocabularyId());
			result = cs.executeUpdate();
		} catch (Exception e) {
//			e.printStackTrace();
			System.err.println("Update Relatives failed");
		}
		return result;
	}

	@Override
	/**
	 * @return 0 for delete failed
	 * @return 1 for delete successfully
	 */
	public Integer delete(RelativeWord rel) {
		Integer result = 0;
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call deleteRel(?)}");
		) {
			cs.setInt(1, rel.getId());
			result = cs.executeUpdate();
		} catch (Exception e) {
//			e.printStackTrace();
			System.err.println("Delete a Relatives failed");
		}
		return result;
	}

}
