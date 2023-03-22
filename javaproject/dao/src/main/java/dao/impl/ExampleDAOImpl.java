package dao.impl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import database.ConnectDBFromProperties;
import entity.Example;
import dao.ExampleDAO;

public class ExampleDAOImpl extends AbstractDAO<Example> implements ExampleDAO {
	@Override
	/**
	 * @return null if it doesn't exist
	 */
	public Example select(Integer id) {
		Example ex = null;
		try(
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call selEx(?)}");
		){
			cs.setInt(1, id);
			var rs = cs.executeQuery();
			
			if(rs.next()) {
				Integer exId = rs.getInt(1);
				String content = rs.getString(2);
				String meaning = rs.getString(3);
				Integer meaningId = rs.getInt(4);
				ex = new Example(exId, content, meaning, meaningId);
			}
		} catch(Exception e) {
//			e.printStackTrace();
			System.err.println("Select A Example Failed!");
		}
		return ex;
	}

	@Override
	public List<Example> selectAll() {
		List<Example> list = new ArrayList<>();
		try(
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call selAllEx}");
			var rs = cs.executeQuery();
		){
			while(rs.next()) {
				Integer exId = rs.getInt(1);
				String content = rs.getString(2);
				String meaning = rs.getString(3);
				Integer meaningId = rs.getInt(4);
				list.add(new Example(exId, content, meaning, meaningId));
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.err.println("Select all Example failed!");
		}
		return list;
	}

	@Override
	/**
	 * @return 0 for insert failed
	 * @return 1 for insert successfully
	 */
	public Integer insert(Example ex) {
		Integer result = 0;
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call insertEx(?, ?, ?)}")
		){
			cs.setString(1, ex.getContent());
			if(ex.getMeaning() != null) {
				cs.setString(2, ex.getMeaning());
			} else {
				cs.setNull(2, Types.NVARCHAR);
			}
			cs.setInt(3, ex.getMeaningId());
			result = cs.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Insert Example failed!");
		}
		return result;
	}

	@Override
	/**
	 * @return 0 for update failed
	 * @return 1 for update successfully
	 */
	public Integer update(Example ex) {
		Integer result = 0;
		try(
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call updateEx(?, ?, ?, ?)}");
		){
			cs.setInt(1, ex.getId());
			cs.setString(2, ex.getContent());
			if(ex.getMeaning() != null) {
				cs.setString(3, ex.getMeaning());
			} else {
				cs.setNull(3, Types.NVARCHAR);
			}
			cs.setInt(4, ex.getMeaningId());
			result = cs.executeUpdate();
		} catch(Exception e) {
//			e.printStackTrace();
			System.err.println("Update Example failed");
		}
		return result;
	}

	@Override
	/**
	 * @return 0 for delete failed
	 * @return 1 for delete successfully
	 */
	public Integer delete(Example ex) {
		Integer result = 0;
		try(
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call deleteEx(?)}");
		){
			cs.setInt(1, ex.getId());
			result = cs.executeUpdate();
		} catch(Exception e) {
//			e.printStackTrace();
			System.err.println("Delete a Example failed");
		}
		return result;
	}

	

}
