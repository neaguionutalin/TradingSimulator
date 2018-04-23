package main.com.m3c.gp.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AuthenticationDAO {
	// If email exists in the database table 'Clients' return true
	public boolean emailExists(String email) {
		Connection conn = DBManager.getConnection();
		ResultSet resultSet;

		try {
			PreparedStatement preparedStatement = conn.prepareStatement(SqlQueries.EMAIL_EXISTS_QUERY);
			preparedStatement.setString(1, email);
			resultSet = preparedStatement.executeQuery();

			return resultSet.last();
		} catch (SQLException e) {
			System.out.println("DBManager: readAllClientOrders() failed - " + e.getMessage());
		}
		return false;
	}

	// if email and password match return true
	public boolean emailPasswordMatch(String email, String password) {
		Connection conn = DBManager.getConnection();
		ResultSet resultSet;

		try {
			PreparedStatement preparedStatement = conn.prepareStatement(SqlQueries.MATCH_EMAIL_PASSWORD_QUERY);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();

			return resultSet.last();
		} catch (SQLException e) {
			System.out.println("DBManager: readAllClientOrders() failed - " + e.getMessage());
		}
		return false;
	}
}