package main.com.m3c.gp.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;

import main.com.m3c.gp.model.*;

public class Service {
	private static Connection conn = null;

	public static void insertOrder(Instrument instrument, int clientId, double price, int quantity, OrderType type) {
		OrderDAO orderDAO = new OrderDAO();
		orderDAO.insertOrder(new Order(instrument, clientId, price, quantity, type));
	}

	// Inserts a Client's details into the Database table 'Clients'

	public static void insertClient(String firstname, String lastname, String email, String pass) {
		ClientDAO clientDAO = new ClientDAO();
		clientDAO.insertClient(new Client(firstname, lastname, email, pass));
	}

	// Retrieves a Order object from the Database table 'Orders'
	public static Order getOrder(int orderId) {
		conn = DBManager.getConnection();

		return null;
	}

	// Retrieves a Client object from the Database table 'Clients'

	public static ClientDTO getClient(int clientId) {
		conn = DBManager.getConnection();
		ClientDTO client = null;
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(SqlQueries.CLIENT_QUERY);
			preparedStatement.setInt(1, clientId);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				int clientID = resultSet.getInt("CLIENT_ID");
				String first_name = resultSet.getString("FIRST_NAME");
				String last_name = resultSet.getString("LAST_NAME");
				String email = resultSet.getString("EMAIL");
				String password = resultSet.getString("PASSWORD");
				String typeOfUser = resultSet.getString("USER_GROUP");
				UserGroup userGroup = null;
				if (typeOfUser == "USER") {
					userGroup = UserGroup.USER;
				} else {
					userGroup = UserGroup.ADMIN;
				}
				Double bugdet = resultSet.getDouble("BUDGET");
				client = new ClientDTO(clientID, first_name, last_name, email, password, bugdet, userGroup);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return client;
	}

	// Returns a List of Orders for a given clientId from the 'Orders' table
	public static List<OrderDTO> getClientOrders(int clientId) {
		ClientDAO clientDAO = new ClientDAO();
		return clientDAO.getClientOrders(clientId);
	}

	// If email exists in the database table 'Clients' return true
	public static boolean emailExists(String email) {
		AuthenticationDAO authenticationDAO = new AuthenticationDAO();
		return authenticationDAO.emailExists(email);
	}

	// if email and password match return true
	public static boolean emailPasswordMatch(String email, String password) {
		AuthenticationDAO authenticationDAO = new AuthenticationDAO();
		return authenticationDAO.emailPasswordMatch(email, password);
	}
}
