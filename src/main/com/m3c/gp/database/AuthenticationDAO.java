package main.com.m3c.gp.database;

import java.sql.Connection;

/**
 * Author: Metin Dagcilar, Ali Saleem
 * Date: 19/04/18
 * Database Manager interface
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticationDAO {
	// If email exists in the database table 'Clients' return true
	public boolean emailExists(String email) throws ConnectionNotFoundException {
		ResultSet resultSet;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			throw new ConnectionNotFoundException("Connection to database failed when registering a new client");
		}
		try (Connection conn = new DBManager().getConnection()) {
			PreparedStatement preparedStatement = conn.prepareStatement(SqlQueries.EMAIL_EXISTS_QUERY);
			preparedStatement.setString(1, email);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				if(resultSet.getString("email") != null) {
					return true;
				}
			}
		} catch (SQLException e) {
			throw new ConnectionNotFoundException("AuthenticationDAO: emailExists() failed - " + e.getMessage());
		}
		return false;
	}

	// if email and password match return true
	public boolean emailPasswordMatch(String email, String password) throws ConnectionNotFoundException {
		ResultSet resultSet;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			throw new ConnectionNotFoundException("Connection to database failed when trying to log in a client");
		}
		try (Connection conn = new DBManager().getConnection()) {
			PreparedStatement preparedStatement = conn.prepareStatement(SqlQueries.MATCH_EMAIL_PASSWORD_QUERY);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();

			return resultSet.last();
		} catch (SQLException e) {
			throw new ConnectionNotFoundException("AuthenticationDAO: emailPasswordMatch() failed - " + e.getMessage());
		}
	}
}
