package dao.impl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import dao.PhoneticDAO;
import database.ConnectDBFromProperties;
import entity.Phonetic;
import entity.Question;

public class PhoneticDAOImpl extends AbstractDAO<Phonetic> implements PhoneticDAO {

	@Override
	public Phonetic select(Integer id) {
		Phonetic pnt = null;
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call selPhonetic(?)}");
		) {
			cs.setInt(1, id);
			var rs = cs.executeQuery();

			if (rs.next()) {
				Integer pntId = rs.getInt(1);
				String content = rs.getString(2);
				Integer vocabId = rs.getInt(3);
				pnt = new Phonetic(pntId, content, vocabId);
			}
		} catch (Exception e) {
			 e.printStackTrace();
			System.err.println("Select A Phonetic Failed!");
		}
		return pnt;
	}

	@Override
	public List<Phonetic> selectAll() {
		List<Phonetic> list = new ArrayList<>();
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call selAllPhonetic}");
			var rs = cs.executeQuery();
		) {
			while (rs.next()) {
				Integer pntId = rs.getInt(1);
				String content = rs.getString(2);
				Integer vocabId = rs.getInt(3);
				list.add(new Phonetic(pntId, content, vocabId));
			}
		} catch (Exception e) {
			// e.printStackTrace();
			System.err.println("Select all Phonetics failed!");
		}
		return list;
	}

	@Override
	public Integer insert(Phonetic pnt) {
		Integer result = 0;
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call insertPhonetic(?, ?)}")
		) {
			if(pnt.getContent() != null) {
				cs.setString(1, pnt.getContent());
			} else {
				cs.setNull(1, Types.NVARCHAR);
			}
			cs.setInt(2, pnt.getVocabId());
			result = cs.executeUpdate();
		} catch (Exception e) {
			// e.printStackTrace();
			System.err.println("Insert Phonetic failed!");
		}
		return result;
	}

	@Override
	public Integer update(Phonetic pnt) {
		Integer result = 0;
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call updatePhonetic(?, ?, ?)}");
		) {
			cs.setInt(1, pnt.getId());
			if(pnt.getContent() != null) {
				cs.setString(2, pnt.getContent());
			} else {
				cs.setNull(2, Types.NVARCHAR);
			}
			cs.setInt(3, pnt.getVocabId());
			result = cs.executeUpdate();
		} catch (Exception e) {
			// e.printStackTrace();
			System.err.println("Update Phonetic failed");
		}
		return result;
	}

	@Override
	public Integer delete(Phonetic pnt) {
		Integer result = 0;
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call deletePhonetic(?)}");
		) {
			cs.setInt(1, pnt.getId());
			result = cs.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Delete a Phonetic failed");
		}
		return result;
	}

	

}
