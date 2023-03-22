package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class CallableStatementUtils {
	public static CallableStatement createCS(Connection connect, String sql, int pageNumber, int rowsOfPage) throws SQLException {
		var cs = connect.prepareCall(sql);
		cs.setInt(1, pageNumber);
		cs.setInt(2, rowsOfPage);
		return cs;
	}
}
