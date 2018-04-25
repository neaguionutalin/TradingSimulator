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
	
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(DBManager.class);
	private Connection conn = null;

	private static final String USER_NAME = "boss";
	private static final String PASSWORD = "Pa55w0rd";
	private static final String PUBLIC_DNS = "18.222.52.244";
	private static final String PORT_NUMBER = "3306";
	private static final String DATABASE_NAME = "Trading_Simulator";

	private static final String DB_HOSTNAME = "jdbc:mysql://" + PUBLIC_DNS + ":" + PORT_NUMBER + "/" + DATABASE_NAME;

	//Get the current connection, if not create a connection
	public Connection getConnection() throws ConnectionNotFoundException {
		if (conn != null)
			return conn;
		return connect();
	}

	//Initialise connection to database
	private Connection connect() throws ConnectionNotFoundException {
			try {
				conn = DriverManager.getConnection(DB_HOSTNAME, USER_NAME, PASSWORD);
			} catch (SQLException e) {
				logger.error("DBManager: Connection to database failed - " + e.getMessage());
				throw new ConnectionNotFoundException("DBManager: Connection to database failed - " + e.getMessage());
			}
		return conn;
	}
}