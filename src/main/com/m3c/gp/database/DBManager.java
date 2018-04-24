package main.com.m3c.gp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Author: Metin Dagcilar, Ali Saleem
 * Date: 19/04/18
 * Database Manager interface
 */

public class DBManager {

	private Connection conn = null;

	private static final String USER_NAME = "boss";
	private static final String PASSWORD = "Pa55w0rd";
	private static final String PUBLIC_DNS = "18.217.124.114";
	private static final String PORT_NUMBER = "3306";
	private static final String DATABASE_NAME = "Trading_Simulator";

	private static final String DB_HOSTNAME = "jdbc:mysql://" + PUBLIC_DNS + ":" + PORT_NUMBER + "/" + DATABASE_NAME;

	/**
	 * Get the current connection, if not create a connection
	 * 
	 * @return Connection
	 */
	public Connection getConnection() {
		if (conn != null)
			return conn;
		return connect();
	}

	/**
	 * Initialise connection to database
	 *
	 * @return Connection
	 */
	private Connection connect() {
		try {
			conn = DriverManager.getConnection(DB_HOSTNAME, USER_NAME, PASSWORD);
		} catch (Exception e) {
			System.err.println("DBManager: Connection to database failed - " + e.getMessage());
		}
		return conn;
	}

	// Close the connection to the database
	private void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				System.err.println("DBManager: Closing database connection failed - " + e.getMessage());
			}
		}
	}
}