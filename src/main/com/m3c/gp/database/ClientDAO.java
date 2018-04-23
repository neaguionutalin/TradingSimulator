package main.com.m3c.gp.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import java.sql.Connection;

import main.com.m3c.gp.model.Client;
import main.com.m3c.gp.model.Instrument;
import main.com.m3c.gp.model.OrderType;
import main.com.m3c.gp.model.UserGroup;

/**
 * Author: Metin Dagcilar, Ali Saleem
 * Date: 19/04/18
 * Database Manager interface
 */

@Named
public class ClientDAO {

	public void insertClient(Client client) {
		

		try (Connection conn = DBManager.getConnection()) {
			PreparedStatement preparedStatement = conn.prepareStatement(SqlQueries.INSERT_CLIENT_QUERY);

			// Auto-increment
			// preparedStatement.setString(1, String.valueOf(client.getClientId()));

			preparedStatement.setString(2, client.getFirstName());
			preparedStatement.setString(3, client.getLastName());
			preparedStatement.setString(4, client.getEmail());
			preparedStatement.setString(5, client.getPassword());
			preparedStatement.setString(6, client.getUserGroup().toString());
			preparedStatement.setDouble(7, client.getclientBalance());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.err.println("ClientDAO: Insert Client details to database failed - " + e.getMessage());
		}
	}
	

	public ClientDTO getClient(String email) {

		try (Connection conn = DBManager.getConnection()){
			PreparedStatement preparedStatement = conn.prepareStatement(SqlQueries.CLIENT_QUERY);
			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				int clientID = resultSet.getInt("CLIENT_ID");
				String first_name = resultSet.getString("FIRST_NAME");
				String last_name = resultSet.getString("LAST_NAME");
				String password = resultSet.getString("PASSWORD");
				String typeOfUser = resultSet.getString("USER_GROUP");
				
				UserGroup userGroup = null;
				if (typeOfUser == "USER") {
					userGroup = UserGroup.USER;
				} else {
					userGroup = UserGroup.ADMIN;
				}
				
				Double bugdet = resultSet.getDouble("BUDGET");
				return new ClientDTO(clientID, first_name, last_name, email, password, bugdet, userGroup);
			}
		} catch (SQLException e) {
			System.err.println("ClientDAO: getClient() failed - " + e.getMessage());
		}
		return null;
	}

	// Returns a List of Orders for a given clientId from the 'Orders' table
	public List<OrderDTO> getClientOrders(int clientId) {
		ResultSet resultSet = readAllClientOrders(clientId);
		List<OrderDTO> clientOrders = new ArrayList<>();

		try {
			while (resultSet.next()) {
				int orderId = Integer.valueOf(resultSet.getString("order_id"));
				Instrument instrument = new Instrument(resultSet.getString("instrument_ticker"),
						resultSet.getString("instrument_name"));
				double price = Double.valueOf(resultSet.getString("price"));
				int quantity = Integer.valueOf(resultSet.getString("quantity"));
				String type = resultSet.getString("type");

				OrderType orderType;
				if (type.toUpperCase().equals("BUY")) {
					orderType = OrderType.BUY;
				} else {
					orderType = OrderType.SELL;
				}

				clientOrders.add(new OrderDTO(orderId, instrument, clientId, price, quantity, orderType));
			}

			return clientOrders;
		} catch (SQLException e) {
			System.err.println("ClientDAO: getClientOrders() failed - " + e.getMessage());
		}

		return clientOrders;
	}

	// Returns a ResultSet of all the Orders place by a single client
	private ResultSet readAllClientOrders(int clientId) {
		

		try (Connection conn = DBManager.getConnection()) {
			Statement statement = conn.createStatement();
			return statement.executeQuery(SqlQueries.CLIENT_ORDERS_QUERY);

		} catch (SQLException e) {
			System.out.println("ClientDAO: readAllClientOrders() failed - " + e.getMessage());
		}
		return null;
	}
}
