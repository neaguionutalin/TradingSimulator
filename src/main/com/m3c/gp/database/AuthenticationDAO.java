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
	public boolean emailExists(String email) throws ClassNotFoundException {

		ResultSet resultSet;
		Class.forName("com.mysql.cj.jdbc.Driver");
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
			System.out.println("AuthenticationDAO: emailExists() failed - " + e.getMessage());
		}
		return false;
	}

	// if email and password match return true
	public boolean emailPasswordMatch(String email, String password) throws ClassNotFoundException {
		ResultSet resultSet;
		Class.forName("com.mysql.cj.jdbc.Driver");
		try (Connection conn = new DBManager().getConnection()) {
			PreparedStatement preparedStatement = conn.prepareStatement(SqlQueries.MATCH_EMAIL_PASSWORD_QUERY);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();

			return resultSet.last();
		} catch (SQLException e) {
			System.out.println("AuthenticationDAO: emailPasswordMatch() failed - " + e.getMessage());
		}
		return false;
	}
}
