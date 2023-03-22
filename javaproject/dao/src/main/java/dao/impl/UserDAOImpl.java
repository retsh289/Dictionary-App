package dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.mindrot.jbcrypt.BCrypt;

import dao.UserDAO;
import database.CallableStatementUtils;
import database.ConnectDBFromProperties;
import entity.Bookmark;
import entity.History;
import entity.User;
import entity.Vocabulary;

public class UserDAOImpl extends AbstractDAO<User> implements UserDAO {

	@Override
	public List<User> getList(int level) {
		List<User> list = new ArrayList<>();
		Connection con = null;
		CallableStatement cs = null;
		User user;
		try {
			con = ConnectDBFromProperties.getConnectionFromClassPath();
			cs = con.prepareCall("{call selUserByLevel(?)}");
			cs.setInt(1, level);
			ResultSet rs = cs.executeQuery();

			while (rs.next()) {
				Integer userId = rs.getInt(1);
				String email = rs.getString(2);
				Integer roleId = rs.getInt(3);
				LocalDate createdAt = LocalDate.parse(rs.getDate(4).toString(),
						DateTimeFormatter.ofPattern("[yyyy-MM-dd]"));
				LocalDate updatedAt = LocalDate.parse(rs.getDate(5).toString(),
						DateTimeFormatter.ofPattern("[yyyy-MM-dd]"));
				user = new User(userId, email, null, roleId);
				user.setCreatedAt(createdAt);
				user.setUpdatedAt(updatedAt);
				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) {
					con.close();
					cs.close();
				}
			} catch (Exception e2) {
				// e2.printStackTrace();
				System.out.println("Get list " + (level == 1 ? "Admin" : "User") + "Failed");
			}
		}

		return list;
	}

	@Override
	/**
	 * @return null if it doesn't exists
	 */
	public User select(Integer id) {
		User user = null;
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call selUser(?)}");
		) {
			cs.setInt(1, id);
			var rs = cs.executeQuery();

			if (rs.next()) {
				Integer userId = rs.getInt(1);
				String email = rs.getString(2);
				Integer roleId = rs.getInt(3);
				LocalDate createdAt = LocalDate.parse(rs.getDate(4).toString(),
						DateTimeFormatter.ofPattern("[yyyy-MM-dd]"));
				LocalDate updatedAt = LocalDate.parse(rs.getDate(5).toString(),
						DateTimeFormatter.ofPattern("[yyyy-MM-dd]"));
				user = new User(userId, email, null, roleId);
				user.setCreatedAt(createdAt);
				user.setUpdatedAt(updatedAt);

			} else {
				return null;
			}
		} catch (Exception e) {
			// e.printStackTrace();
			System.err.println("Get a user failed!");
		}
		return user;
	}

	@Override
	/**
	 * @return null if doesn't have any
	 */
	public List<User> selectAll() {
		List<User> list = new ArrayList<>();
		User user;

		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call selAllUser}");
			var rs = cs.executeQuery();
		) {
			while (rs.next()) {
				Integer userId = rs.getInt(1);
				String email = rs.getString(2);
				Integer roleId = rs.getInt(3);
				LocalDate createdAt = LocalDate.parse(rs.getDate(4).toString(),
						DateTimeFormatter.ofPattern("[yyyy-MM-dd]"));
				LocalDate updatedAt = LocalDate.parse(rs.getDate(5).toString(),
						DateTimeFormatter.ofPattern("[yyyy-MM-dd]"));
				user = new User(userId, email, null, roleId);
				user.setCreatedAt(createdAt);
				user.setUpdatedAt(updatedAt);
				list.add(user);
			}
		} catch (Exception e) {
			// e.printStackTrace();
			System.err.println("Get list of user failed!");
		}

		return list;
	}

	@Override
	/**
	 * @return 0 for insert failed
	 * @return 1 for insert successfully
	 */
	public Integer insert(User user) {
		if (!checkExistEmail(user.getEmail())) {
			String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
			Integer result = 0;
			try (
				var con = ConnectDBFromProperties.getConnectionFromClassPath();
				var cs = con.prepareCall("{call insertUser(?, ?, ?)}");
			) {
				// must be validate before insert
				cs.setString(1, user.getEmail());
				cs.setString(2, hashed);
				cs.setInt(3, user.getRoleId());

				result = cs.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("email da ton tai asd ");
			}
			return result;
		} else {
			JOptionPane.showMessageDialog(null, "Tên Đăng Nhập Đã tồn Taij");
		}
		return 0;
	}

	@Override
	public boolean checkExistEmail(String email) {
		boolean result = false;
		try (var con = ConnectDBFromProperties.getConnectionFromClassPath();
				var cs = con.prepareCall("{call selUserIfExist(?)}");) {
			cs.setString(1, email);
			var rs = cs.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			// e.printStackTrace();
			System.err.println("vui long kiem tra lai du lieu");
		}
		return result;
	}

	public static String getPassFromDbById(Integer id) {
		try (var con = ConnectDBFromProperties.getConnectionFromClassPath();
				PreparedStatement stmt = con.prepareStatement("SELECT PASSWORD FROM [USER] where id= ?");) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getPassFromDbByAccount(String acc) {
		try (var con = ConnectDBFromProperties.getConnectionFromClassPath();
				PreparedStatement stmt = con.prepareStatement("SELECT PASSWORD FROM [USER] where EMAIL= ?");) {
			stmt.setString(1, acc);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return BCrypt.hashpw(acc, BCrypt.gensalt());
	}
	
	public static int getIdFromDbByAccount(String acc) {
		int result = -1;
		try (var con = ConnectDBFromProperties.getConnectionFromClassPath();
				PreparedStatement stmt = con.prepareStatement("SELECT ID FROM [USER] where EMAIL= ?");) {
			stmt.setString(1, acc);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * @return 0 for update private info failed
	 * @return 1 or 2 for update private info successfully
	 */
	public static int getLevelFromUser(User u) {
		int result = 0;
		try (var con = ConnectDBFromProperties.getConnectionFromClassPath();
				var cs = con.prepareCall("{call selLevelByUserEmail(?)}");) {
			cs.setString(1, u.getEmail());
			var rs = cs.executeQuery();
			if (rs.next()) {
				Integer check = rs.getInt(1);
				return check;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Select level By User Email Failed");
		}
		return 0;
	}

	public static boolean loginDb(User u) {
		try {
			if (BCrypt.checkpw(u.getPassword(), getPassFromDbByAccount(u.getEmail()))) {
				u.setRoleId(getLevelFromUser(u));
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Tài Khoản Hoặc Mật Khẩu Không Chính Xác !");
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Đăng Nhập Thất Bại Vui Lòng Kiểm Tra Lại Dữ liệu!");
		}
		return false;
	}

	@Override
	@Deprecated
	public Integer update(User user) {
		return 0;
	}


	@Override
	/**
	 * @return 0 for update private info failed
	 * @return 1 for update private info successfully
	 */
	public Integer updatePassword(User user) {
		Integer result = 0;
		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		try (var con = ConnectDBFromProperties.getConnectionFromClassPath();
				var cs = con.prepareCall("{call updatePasswordInfoUser(?, ?)}");) {
			cs.setInt(1, user.getId());
			cs.setString(2, hashed);
			result = cs.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Update User Password Failed");
		}
		return result;
	}
	
	@Override
	/**
	 * @return 1 if success
	 * @return 0 if failed
	 */
	public Integer delete(User user) {
		Integer result = 0;
		try (var con = ConnectDBFromProperties.getConnectionFromClassPath();
				var cs = con.prepareCall("{call deleteUser(?)}");) {
			cs.setInt(1, user.getId());
			result = cs.executeUpdate();
		} catch (Exception e) {
			 e.printStackTrace();
			System.out.println("Delete a user failed!");
		}
		return result;
	}

	@Override
	public History selectHistoryByUserId(Integer userId) {
		History hst = null;
		try (var con = ConnectDBFromProperties.getConnectionFromClassPath();
				var cs = con.prepareCall("{call selHistoryByUserId(?)}");) {
			cs.setInt(1, userId);
			var rs = cs.executeQuery();
			if (rs.next()) {
				Integer bmId = rs.getInt(1);
				Integer vocabId = rs.getInt(2);
				Integer userIdRs = rs.getInt(3);
				hst = new History(bmId, vocabId, userIdRs);
			}
		} catch (Exception e) {
			// e.printStackTrace();
			System.err.println("Select History By User Id failed");
		}
		return hst;
	}

	@Override
	public Bookmark selectBookmarkByUserId(Integer userId) {
		Bookmark bm = null;
		try (var con = ConnectDBFromProperties.getConnectionFromClassPath();
				var cs = con.prepareCall("{call selBookmarkByUserId(?)}");) {
			cs.setInt(1, userId);
			var rs = cs.executeQuery();
			if (rs.next()) {
				Integer bmId = rs.getInt(1);
				Integer vocabId = rs.getInt(2);
				Integer userIdRs = rs.getInt(3);
				bm = new Bookmark(bmId, vocabId, userIdRs);
			}
		} catch (Exception e) {
			// e.printStackTrace();
			System.err.println("Select Bookmark By User Id failed");
		}
		return bm;
	}

	@Override
	public List<User> selectUserByPages(int pageNumber, int rowOfPages) {
		List<User> list = new ArrayList<>();
		User user;
		try (var con = ConnectDBFromProperties.getConnectionFromClassPath();
				var cs = CallableStatementUtils.createCS(con, "{call selUserByPages(?, ?)}", pageNumber, rowOfPages);
				var rs = cs.executeQuery();) {
			while (rs.next()) {
				Integer userId = rs.getInt(1);
				String email = rs.getString(2);
				Integer roleId = rs.getInt(3);
				LocalDate createdAt = LocalDate.parse(rs.getDate(4).toString(),
						DateTimeFormatter.ofPattern("[yyyy-MM-dd]"));
				LocalDate updatedAt = LocalDate.parse(rs.getDate(5).toString(),
						DateTimeFormatter.ofPattern("[yyyy-MM-dd]"));
				user = new User(userId, email, null, roleId);
				user.setCreatedAt(createdAt);
				user.setUpdatedAt(updatedAt);
				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Select User By Pages Failed");
		}
		return list;
	}

	@Override
	public Integer countNumberOfUser() {
		int count = 0;

		try (var con = ConnectDBFromProperties.getConnectionFromClassPath();
				var cs = con.prepareCall("{call countUser}");
				var rs = cs.executeQuery();) {
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;
	}

	@Override
	public List<User> selectAdminByPages(int pageNumber, int rowOfPages) {
		List<User> list = new ArrayList<>();
		User user;
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = CallableStatementUtils.createCS(con, "{call selAdminByPages(?, ?)}", pageNumber, rowOfPages);
			var rs = cs.executeQuery();
		) {
			while (rs.next()) {
				Integer userId = rs.getInt(1);
				String email = rs.getString(2);
				Integer roleId = rs.getInt(3);
				LocalDate createdAt = LocalDate.parse(rs.getDate(4).toString(),
						DateTimeFormatter.ofPattern("[yyyy-MM-dd]"));
				LocalDate updatedAt = LocalDate.parse(rs.getDate(5).toString(),
						DateTimeFormatter.ofPattern("[yyyy-MM-dd]"));
				user = new User(userId, email, null, roleId);
				user.setCreatedAt(createdAt);
				user.setUpdatedAt(updatedAt);
				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Select Admin By Pages Failed");
		}
		return list;
	}

	@Override
	public Integer countNumberOfAdmin() {
		int count = 0;

		try (var con = ConnectDBFromProperties.getConnectionFromClassPath();
				var cs = con.prepareCall("{call countAdmin}");
				var rs = cs.executeQuery();) {
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;
	}

	@Override
	public Integer selectIdByUserEmail(String email) {
		Integer id = null;
		try (var con = ConnectDBFromProperties.getConnectionFromClassPath();
				var cs = con.prepareCall("{call selIdByUserEmail(?)}");) {
			cs.setString(1,email );
			var rs = cs.executeQuery();
			System.out.println(rs);
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			 e.printStackTrace();
			System.err.println("Select id By User email failed");
		}
		return id;
	}

	@Override
	public Integer updateRole(User user) {
		Integer result = 0;
		try (var con = ConnectDBFromProperties.getConnectionFromClassPath();
				var cs = con.prepareCall("{call updateRoleUser(?, ?)}");) {
			cs.setInt(1, user.getId());
			cs.setInt(2, user.getRoleId());
			result = cs.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Update User Role Failed");
		}
		return result;
	}

	@Override
	public List<User> searchAll(String str, Integer roleId) {
		List<User> list = new ArrayList<>();
		try(
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call searchAllUser(?, ?)}");
		){
			cs.setString(1,str );
			cs.setInt(2, roleId);
			var rs = cs.executeQuery();
			User user;
			while(rs.next()) {
				Integer userId = rs.getInt(1);
				String email = rs.getString(2);
				Integer roleIdRs = rs.getInt(3);
				LocalDate createdAt = LocalDate.parse(rs.getDate(4).toString(),
						DateTimeFormatter.ofPattern("[yyyy-MM-dd]"));
				LocalDate updatedAt = LocalDate.parse(rs.getDate(5).toString(),
						DateTimeFormatter.ofPattern("[yyyy-MM-dd]"));
				user = new User(userId, email, null, roleIdRs);
				user.setCreatedAt(createdAt);
				user.setUpdatedAt(updatedAt);
				list.add(user);
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.err.println("search all user failed!");
		}
		return list;
	}


	
}