package dao.impl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import dao.UserLessonResultDAO;
import database.ConnectDBFromProperties;
import entity.Feedback;
import entity.UserLessonResult;

public class UserLessonResultDAOImpl extends AbstractDAO<UserLessonResult> implements UserLessonResultDAO {

	@Override
	public UserLessonResult select(Integer id) {
		UserLessonResult ulr = null;
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call selUserLessonResult(?)}");
		) {
			cs.setInt(1, id);
			var rs = cs.executeQuery();

			if (rs.next()) {
				Integer ulrId = rs.getInt(1);
				Integer userId = rs.getInt(2);
				Integer lessonId = rs.getInt(3);
				Integer point = rs.getInt(4);
				ulr = new UserLessonResult(ulrId, userId, lessonId, point);
			}
		} catch (Exception e) {
			 e.printStackTrace();
			System.err.println("Select A UserLessonResult Failed!");
		}
		return ulr;
	}

	@Override
	public List<UserLessonResult> selectAll() {
		List<UserLessonResult> list = new ArrayList<>();
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call selAllUserLessonResult}");
			var rs = cs.executeQuery();
		) {
			while (rs.next()) {
				Integer ulrId = rs.getInt(1);
				Integer userId = rs.getInt(2);
				Integer lessonId = rs.getInt(3);
				Integer point = rs.getInt(4);
				list.add(new UserLessonResult(ulrId, userId, lessonId, point));
			}
		} catch (Exception e) {
			// e.printStackTrace();
			System.err.println("Select all Feedback failed!");
		}
		return list;
	}

	@Override
	public Integer insert(UserLessonResult ulr) {
		Integer result = 0;
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call insertUserLessonResult(?, ?, ?)}")
		) {
			cs.setInt(1, ulr.getUserId());
			cs.setInt(2, ulr.getLessonId());
			cs.setInt(3, ulr.getPoint());
			result = cs.executeUpdate();
		} catch (Exception e) {
			// e.printStackTrace();
			System.err.println("Insert UserLessonResult failed!");
		}
		return result;
	}

	@Override
	public Integer update(UserLessonResult ulr) {
		Integer result = 0;
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call updateUserLessonResult(?, ?, ?, ?)}");
		) {
			cs.setInt(1, ulr.getId());
			cs.setInt(2, ulr.getUserId());
			cs.setInt(3, ulr.getLessonId());
			cs.setInt(4, ulr.getPoint());
			result = cs.executeUpdate();
		} catch (Exception e) {
			// e.printStackTrace();
			System.err.println("Update UserLessonResult failed");
		}
		return result;
	}

	@Override
	public Integer delete(UserLessonResult ulr) {
		Integer result = 0;
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call deleteUserLessonResult(?)}");
		) {
			cs.setInt(1, ulr.getId());
			result = cs.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Delete a UserLessonResult failed");
		}
		return result;
	}

	@Override
	public UserLessonResult find(Integer userId, Integer lessonId) {
		UserLessonResult ulr = null;
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call findUserLessonResult(?, ?)}");
		) {
			cs.setInt(1, userId);
			cs.setInt(2, lessonId);
			var rs = cs.executeQuery();

			if (rs.next()) {
				Integer ulrId = rs.getInt(1);
				Integer userIdRs = rs.getInt(2);
				Integer lessonIdRs = rs.getInt(3);
				Integer point = rs.getInt(4);
				ulr = new UserLessonResult(ulrId, userIdRs, lessonIdRs, point);
			}
		} catch (Exception e) {
			 e.printStackTrace();
			System.err.println("Find A UserLessonResult Failed!");
		}
		return ulr;
	}

}
