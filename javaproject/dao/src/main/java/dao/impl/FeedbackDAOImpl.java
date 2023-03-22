package dao.impl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import dao.FeedbackDAO;
import database.CallableStatementUtils;
import database.ConnectDBFromProperties;
import entity.Bookmark;
import entity.Feedback;
import entity.User;
import entity.VocabularyContribution;

public class FeedbackDAOImpl extends AbstractDAO<Feedback> implements FeedbackDAO {

	@Override
	public Feedback select(Integer id) {
		Feedback fb = null;
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call selFeedback(?)}");
		) {
			cs.setInt(1, id);
			var rs = cs.executeQuery();

			if (rs.next()) {
				Integer fbId = rs.getInt(1);
				String content = rs.getString(2);
				Integer userId = rs.getInt(3);
				fb = new Feedback(fbId, content, userId);
			}
		} catch (Exception e) {
			 e.printStackTrace();
			System.err.println("Select A Feedback Failed!");
		}
		return fb;
	}

	@Override
	public List<Feedback> selectAll() {
		List<Feedback> list = new ArrayList<>();
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call selAllFeedback}");
			var rs = cs.executeQuery();
		) {
			while (rs.next()) {
				Integer fbId = rs.getInt(1);
				String content = rs.getString(2);
				Integer userId = rs.getInt(3);
				list.add(new Feedback(fbId, content, userId));
			}
		} catch (Exception e) {
			// e.printStackTrace();
			System.err.println("Select all Feedback failed!");
		}
		return list;
	}

	@Override
	public Integer insert(Feedback fb) {
		Integer result = 0;
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call insertFeedback(?, ?)}")
		) {
			if(fb.getContent() != null) {
				cs.setString(1, fb.getContent());				
			} else {
				cs.setNull(1, Types.NVARCHAR);
			}
			cs.setInt(2, fb.getUserId());
			result = cs.executeUpdate();
		} catch (Exception e) {
			// e.printStackTrace();
			System.err.println("Insert Feedback failed!");
		}
		return result;
	}

	@Override
	public Integer update(Feedback fb) {
		Integer result = 0;
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call updateFeedback(?, ?, ?)}");
		) {
			cs.setInt(1, fb.getId());
			if(fb.getContent() != null) {
				cs.setString(2, fb.getContent());				
			} else {
				cs.setNull(2, Types.NVARCHAR);
			}
			cs.setInt(3, fb.getUserId());
			result = cs.executeUpdate();
		} catch (Exception e) {
			// e.printStackTrace();
			System.err.println("Update Feedback failed");
		}
		return result;
	}

	@Override
	public Integer delete(Feedback fb) {
		Integer result = 0;
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call deleteFeedback(?)}");
		) {
			cs.setInt(1, fb.getId());
			result = cs.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Delete a Feedback failed");
		}
		return result;
	}

	@Override
	public Integer countFeedback() {
		int count = 0;

		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call countFeedback}");
			var rs = cs.executeQuery();
		) {
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;
	}

	@Override
	public List<Feedback> selectByPages(int pageNumber, int rowOfPages) {
		List<Feedback> list = new ArrayList<>();
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = CallableStatementUtils.createCS(con, "{call selFeedbackByPages(?, ?)}", pageNumber, rowOfPages);
			var rs = cs.executeQuery();
		) {
			while (rs.next()) {
				Integer fbId = rs.getInt(1);
				String content = rs.getString(2);
				Integer userId = rs.getInt(3);
				list.add(new Feedback(fbId, content, userId));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Select Feedback By Pages Failed");
		}
		return list;
	}

	
	public Integer delByUserId(Integer userId) {
		Integer result = 0;
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call deleteFeedbackByUserId(?)}");
		) {
			cs.setInt(1, userId);
			result = cs.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Delete History by User Id Failed");
		}
		return result;
	}
	
}
