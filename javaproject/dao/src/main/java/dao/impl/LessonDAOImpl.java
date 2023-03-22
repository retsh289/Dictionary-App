package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import dao.LessonDAO;
import database.CallableStatementUtils;
import database.ConnectDBFromProperties;
import entity.Bookmark;
import entity.Category;
import entity.Lesson;
import entity.Vocabulary;

public class LessonDAOImpl extends AbstractDAO<Lesson> implements LessonDAO {

	@Override
	public Lesson select(Integer id) {
		Lesson ls = null;
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call selLesson(?)}");
		) {
			cs.setInt(1, id);
			var rs = cs.executeQuery();

			if (rs.next()) {
				Integer lsId = rs.getInt(1);
				String title = rs.getString(2);
				String image = rs.getString(3);
				ls = new Lesson(lsId, title, image);
			}
		} catch (Exception e) {
			 e.printStackTrace();
			System.err.println("Select A Lesson Failed!");
		}
		return ls;
	}

	@Override
	public List<Lesson> selectAll() {
		List<Lesson> list = new ArrayList<>();
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call selAllLesson}");
			var rs = cs.executeQuery();
		) {
			while (rs.next()) {
				Integer lsId = rs.getInt(1);
				String title = rs.getString(2);
				String image = rs.getString(3);
				list.add(new Lesson(lsId, title, image));
			}
		} catch (Exception e) {
			// e.printStackTrace();
			System.err.println("Select all Lessons failed!");
		}
		return list;
	}

	@Override
	public Integer insert(Lesson ls) {
		Integer result = 0;
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call insertLesson(?, ?)}")
		) {
			cs.setString(1, ls.getTitle());
			if(ls.getImage() != null) {
				cs.setString(2, ls.getImage());				
			} else{
				cs.setNull(2, Types.NVARCHAR);
			}
			result = cs.executeUpdate();
		} catch (Exception e) {
			// e.printStackTrace();
			System.err.println("Insert Lesson failed!");
		}
		return result;
	}

	@Override
	public Integer update(Lesson ls) {
		Integer result = 0;
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call updateLesson(?, ?, ?)}");
		) {
			cs.setInt(1, ls.getId());
			cs.setString(2, ls.getTitle());
			if(ls.getImage() != null) {
				cs.setString(3, ls.getImage());				
			} else{
				cs.setNull(3, Types.NVARCHAR);
			}
			result = cs.executeUpdate();
		} catch (Exception e) {
			// e.printStackTrace();
			System.err.println("Update Lesson failed");
		}
		return result;
	}

	@Override
	public Integer delete(Lesson ls) {
		Integer result = 0;
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call deleteLesson(?)}");
		) {
			cs.setInt(1, ls.getId());
			result = cs.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Delete a Lesson failed");
		}
		return result;
	}

	@Override
	public Integer countNumberOfLesson() {
		int count = 0;
		
		try(
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call countLesson}");
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
	public List<Lesson> selectByPages(int pageNumber, int rowOfPages) {
		List<Lesson> list = new ArrayList<>();
		
		try(
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = CallableStatementUtils.createCS(con, "{call selLessonByPages(?, ?)}", pageNumber, rowOfPages);
			var rs = cs.executeQuery();
		){
			while(rs.next()) {
				Integer id = rs.getInt(1);
				String title = rs.getString(2);
				String image = rs.getString(3);
				list.add(new Lesson(id, title, image));
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Select Lesson By Pages Failed");
		}
		return list;
	}

	@Override
	public Integer insertGetId(Lesson ls) {
		String sql = "INSERT INTO LESSON VALUES (?, ?)";
		Integer result = 0;
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			PreparedStatement  ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		) {
			ps.setString(1, ls.getTitle());
			if(ls.getImage() != null) {
				ps.setString(2, ls.getImage());				
			} else{
				ps.setNull(2, Types.NVARCHAR);
			}
			int affectedRows = ps.executeUpdate();

	        if (affectedRows == 0) {
	            throw new SQLException("Creating lesson failed, no rows affected.");
	        }

	        try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	result =  generatedKeys.getInt(1);
	            }
	            else {
	                throw new SQLException("Insert get id lesson failed, no ID obtained.");
	            }
	        }
		} catch (Exception e) {
			// e.printStackTrace();
			System.err.println("Insert Get Last Id Lesson failed!");
		}
		return result;
	}

	@Override
	public List<Lesson> searchAll(String str) {
		List<Lesson> list = new ArrayList<>();
		try(
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call searchAllLesson(?)}");
		){
			cs.setString(1,str );
			var rs = cs.executeQuery();
			while(rs.next()) {
				Integer id = rs.getInt(1);
				String title = rs.getString(2);
				String image = rs.getString(3);
				list.add(new Lesson(id, title, image));
			}
		} catch(Exception e) {
//			e.printStackTrace();
			System.err.println("search all vocabulary failed!");
		}
		return list;
	}
	
	public List<Lesson> selectTop3() {
		List<Lesson> list = new ArrayList<>();
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call selTop3Lesson}");
			var rs = cs.executeQuery();
		) {
			while (rs.next()) {
				Integer lsId = rs.getInt(1);
				String title = rs.getString(2);
				String image = rs.getString(3);
				list.add(new Lesson(lsId, title, image));
			}
		} catch (Exception e) {
			// e.printStackTrace();
			System.err.println("Select top 3 Lessons failed!");
		}
		return list;
	}

}
