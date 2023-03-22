package dao.impl;

import java.util.ArrayList;
import java.util.List;

import dao.AnswerDAO;
import database.ConnectDBFromProperties;
import entity.Answer;
import entity.Bookmark;
import entity.Vocabulary;

public class AnswerDAOImpl extends AbstractDAO<Answer> implements AnswerDAO {

	@Override
	public Answer select(Integer id) {
		Answer ans = null;
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call selAnswer(?)}");
		) {
			cs.setInt(1, id);
			var rs = cs.executeQuery();

			if (rs.next()) {
				Integer ansId = rs.getInt(1);
				String content  = rs.getString(2);
				Boolean isTrue  = rs.getBoolean(3);
				Integer questionId = rs.getInt(4);
				ans = new Answer(ansId, content, questionId, isTrue);
			}
		} catch (Exception e) {
			 e.printStackTrace();
			System.err.println("Select A Answer Failed!");
		}
		return ans;
	}

	@Override
	public List<Answer> selectAll() {
		List<Answer> list = new ArrayList<>();
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call selAllAnswer}");
			var rs = cs.executeQuery();
		) {
			while (rs.next()) {
				Integer ansId = rs.getInt(1);
				String content  = rs.getString(2);
				Boolean isTrue  = rs.getBoolean(3);
				Integer questionId = rs.getInt(4);
				list.add(new Answer(ansId, content, questionId, isTrue));
			}
		} catch (Exception e) {
			// e.printStackTrace();
			System.err.println("Select all Answer failed!");
		}
		return list;
	}

	@Override
	public Integer insert(Answer ans) {
		Integer result = 0;
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call insertAnswer(?,?,?)}");
		){
			cs.setString(1, ans.getContent());
			cs.setBoolean(2, ans.getIsTrue());
			cs.setInt(3, ans.getQuestionId());
			result = cs.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Insert a Answer failed");
		}
		return result;
	}

	@Override
	public Integer update(Answer ans) {
		Integer result = 0;
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call updateAnswer(?, ?, ?, ?)}");
		) {
			cs.setInt(1, ans.getId());
			cs.setString(2, ans.getContent());
			cs.setBoolean(3, ans.getIsTrue());
			cs.setInt(4, ans.getQuestionId());
			result = cs.executeUpdate();
		} catch (Exception e) {
			// e.printStackTrace();
			System.err.println("Update Answer failed");
		}
		return result;
	}

	@Override
	public Integer delete(Answer ans) {
		Integer result = 0;
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call deleteAnswer(?)}");
		) {
			cs.setInt(1, ans.getId());
			result = cs.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Delete a Answer failed");
		}
		return result;
	}

	@Override
	public List<Answer> selAllAnswerByQuestionId(Integer questionId) {
		List<Answer> list = new ArrayList<>();
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call selAnswerByQuestionId(?)}");
		) {
			cs.setInt(1, questionId);
			var rs = cs.executeQuery();
			while (rs.next()) {
				Integer ansId = rs.getInt(1);
				String content  = rs.getString(2);
				Boolean isTrue  = rs.getBoolean(3);
				Integer questionIdRs = rs.getInt(4);
				list.add(new Answer(ansId, content, questionIdRs, isTrue));

			}
		} catch (Exception e) {
			// e.printStackTrace();
			System.err.println("Select all Answer by Question Id failed!");
		}
		return list;
	}

}
