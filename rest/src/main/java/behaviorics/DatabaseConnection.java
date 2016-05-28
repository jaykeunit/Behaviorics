package behaviorics;

import java.sql.*;


public class DatabaseConnection implements AutoCloseable {

	Connection conn = null;
	Statement stmt = null;

	public void createConnection(String dbDriver, String dbName, String dbUser, String dbPassword){
		try {
			Class.forName(dbDriver).newInstance();

			conn = DriverManager.getConnection(dbName, dbUser, dbPassword);
		} catch(Exception e){
			System.out.println("Error: " + e);
		}
	}

	public PreparedStatement prepareStatement(String query) throws SQLException {
		return conn.prepareStatement(query);
	}

	public void closeConnection() throws SQLException {
		if(!conn.isClosed())
		{
			conn.close();
		}
	}

	@Override
	public void close() throws Exception {
		closeConnection();
	}

}
