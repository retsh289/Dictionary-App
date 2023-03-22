package dao.impl;

import java.util.List;

import dao.RoleDAO;
import database.ConnectDBFromProperties;
import entity.Answer;
import entity.Role;

public class RoleDAOImpl extends AbstractDAO<Role> implements RoleDAO {

	@Override
	public Role select(Integer id) {
		Role role = null;
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call selRole(?)}");
		) {
			cs.setInt(1, id);
			var rs = cs.executeQuery();

			if (rs.next()) {
				Integer roleId = rs.getInt(1);
				String name = rs.getString(2);
				role = new Role(name);
				role.setId(roleId);
			}
		} catch (Exception e) {
			 e.printStackTrace();
			System.err.println("Select A Answer Failed!");
		}
		return role;
	}

	@Override
	public List<Role> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Deprecated
	public Integer insert(Role t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Deprecated
	public Integer update(Role t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Deprecated
	public Integer delete(Role t) {
		// TODO Auto-generated method stub
		return null;
	}

}
