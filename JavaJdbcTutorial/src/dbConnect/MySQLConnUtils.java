package dbConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnUtils {

	public static Connection getMySQLConnection() throws SQLException, ClassNotFoundException {
		String hostName = "localhost";

		String dbName = "task13";
		String userName = "root";
		String password = "709f2dfe";

		return getMySQLConnection(hostName, dbName, userName, password);
	}

	public static Connection getMySQLConnection(String hostName, String dbName, String userName, String password)
			throws SQLException, ClassNotFoundException {
		String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

		Connection conn = DriverManager.getConnection(connectionURL, userName, password);
		return conn;
	}
}