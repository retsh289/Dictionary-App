package dao.impl;

import java.util.ArrayList;
import java.util.List;

import dao.TheoryDAO;
import database.ConnectDBFromProperties;
import entity.Question;
import entity.Theory;

public class TheoryDAOImpl extends AbstractDAO<Theory> implements TheoryDAO {

	@Override
	public Theory select(Integer id) {
		Theory theory = null;
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call selTheory(?)}");
		) {
			cs.setInt(1, id);
			var rs = cs.executeQuery();

			if (rs.next()) {
				Integer theoryId = rs.getInt(1);
				Integer vocabId = rs.getInt(2);
				Integer lessonId = rs.getInt(3);
				theory = new Theory(theoryId, vocabId, lessonId);
			}
		} catch (Exception e) {
			 e.printStackTrace();
			System.err.println("Select A Theory Failed!");
		}
		return theory;
	}

	@Override
	public List<Theory> selectAll() {
		List<Theory> list = new ArrayList<>();
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call selAllTheory}");
			var rs = cs.executeQuery();
		) {
			while (rs.next()) {
				Integer theoryId = rs.getInt(1);
				Integer vocabId = rs.getInt(2);
				Integer lessonId = rs.getInt(3);
				list.add(new Theory(theoryId, vocabId, lessonId));
			}
		} catch (Exception e) {
			// e.printStackTrace();
			System.err.println("Select all Theories failed!");
		}
		return list;
	}

	@Override
	public Integer insert(Theory theory) {
		Integer result = 0;
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call insertTheory(?, ?)}")
		) {
			cs.setInt(1, theory.getVocabId());
			cs.setInt(2, theory.getLessonId());
			result = cs.executeUpdate();
		} catch (Exception e) {
			// e.printStackTrace();
			System.err.println("Insert Theory failed!");
		}
		return result;
	}

	@Override
	public Integer update(Theory theory) {
		Integer result = 0;
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call updateTheory(?, ?, ?)}");
		) {
			cs.setInt(1, theory.getId());
			cs.setInt(2, theory.getVocabId());
			cs.setInt(3, theory.getLessonId());
			result = cs.executeUpdate();
		} catch (Exception e) {
			// e.printStackTrace();
			System.err.println("Update Theory failed");
		}
		return result;
	}

	@Override
	public Integer delete(Theory theory) {
		Integer result = 0;
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call deleteTheory(?)}");
		) {
			cs.setInt(1, theory.getId());
			result = cs.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Delete a theory failed");
		}
		return result;
	}

	@Override
	public List<Theory> selAllTheoriesByLessonId(Integer lessonId) {
		List<Theory> list = new ArrayList<>();
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call selAllTheoryByLessonId(?)}");
		) {
			cs.setInt(1, lessonId);
			var rs = cs.executeQuery();
			while (rs.next()) {
				Integer theoryId = rs.getInt(1);
				Integer vocabId = rs.getInt(2);
				Integer lessonIdRs = rs.getInt(3);
				list.add(new Theory(theoryId, vocabId, lessonIdRs));
			}
		} catch (Exception e) {
			// e.printStackTrace();
			System.err.println("selAllTheoriesByLessonId failed!");
		}
		return list;
	}

	@Override
	public Integer deleteByVocabId(Integer vocabId) {
		Integer result = 0;
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call deleteTheoryByVocabId(?)}");
		) {
			cs.setInt(1, vocabId);
			result = cs.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Delete a theory failed");
		}
		return result;
	}

}
