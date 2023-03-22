package database;

import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class ConnectDBFromProperties {
	private static final String DB_FILE_NAME = "db.properties";

	private static String getDBFileName() {
		String name = "";
		Path currentDirPath = Paths.get("");
		String paths = currentDirPath.toAbsolutePath().toString();
		String[] temp = paths.split("\\\\");
		List<String> list = new ArrayList<>(Arrays.asList(temp));
		list.remove(list.size() - 1);
		name = list.stream().map(Object::toString).collect(Collectors.joining("\\")) + "\\" + DB_FILE_NAME;
		
		return name;
	}
	
	public static String getConnectionURLFromClassPath(){
		String strCon = null;
		try(
			FileInputStream file = new FileInputStream(getDBFileName());
		){
			Properties prop = new Properties();
			prop.load(file);
			strCon = prop.getProperty("url") + prop.getProperty("serverName")
								+ ":" + prop.getProperty("portNumber")
								+ ";databaseName=" + prop.getProperty("databaseName")
								+ ";user=" + prop.getProperty("username")
								+ ";password=" + prop.getProperty("password");
		} catch (Exception e) {
			e.printStackTrace();
			strCon = null;
		}
		
		return strCon;
	}
	
	public static Connection getConnectionFromClassPath() {
		Connection connect = null;
		try {
			connect = DriverManager.getConnection(getConnectionURLFromClassPath());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Connect DB Failed");
		}
		return connect;
	}
	
}
