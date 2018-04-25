package main.com.m3c.gp.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import java.sql.Connection;

import main.com.m3c.gp.model.Client;
import main.com.m3c.gp.model.Instrument;
import main.com.m3c.gp.model.OrderType;
import main.com.m3c.gp.model.UserGroup;

/**
 * Author: Metin Dagcilar, Ali Saleem Date: 19/04/18 Database Manager interface
 */

@Named
public class ClientDAO {
	
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ClientDAO.class);
	public void insertClient(Client client) throws ConnectionNotFoundException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			logger.error("Connection to database failed when inserting a new client " + e1.getMessage());
			throw new ConnectionNotFoundException("Connection to database failed when inserting a new client");
		}
		try (Connection conn = new DBManager().getConnection()) {
			PreparedStatement preparedStatement = conn.prepareStatement(SqlQueries.INSERT_CLIENT_QUERY);

			preparedStatement.setString(1, client.getFirstName());
			preparedStatement.setString(2, client.getLastName());
			preparedStatement.setString(3, client.getEmail());
			preparedStatement.setString(4, client.getPassword());
			preparedStatement.setString(5, client.getUserGroup().toString());
			preparedStatement.setDouble(6, client.getclientBalance());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			logger.error("ClientDAO: Insert Client details to database failed - " + e.getMessage());
			throw new ConnectionNotFoundException("ClientDAO: Insert Client details to database failed - " + e.getMessage());		}
	}

	public ClientDTO getClient(String email) throws ConnectionNotFoundException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			logger.error("Connection to database failed when trying to retrieve client - " + e1.getMessage());
			throw new ConnectionNotFoundException("Connection to database failed when trying to retrieve client");
		}
		try (Connection conn = new DBManager().getConnection()) {
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
				if (typeOfUser.equals("USER")) {
					userGroup = UserGroup.USER;
				} else
					userGroup = UserGroup.ADMIN;

				Double bugdet = resultSet.getDouble("BUDGET");
				return new ClientDTO(clientID, first_name, last_name, email, password, bugdet, userGroup);
			}
		} catch (SQLException e) {
			logger.error("ClientDAO: getClient() failed - " + e.getMessage());
			throw new ConnectionNotFoundException("ClientDAO: getClient() failed - " + e.getMessage());
		}
		return null;
	}

	// Returns a List of Orders for a given clientId from the 'Orders' table
	public List<OrderDTO> getClientOrders(int clientId) throws ConnectionNotFoundException {
		List<OrderDTO> clientOrders = new ArrayList<>();
		
		try (Connection conn = new DBManager().getConnection()){
			ResultSet resultSet = readAllClientOrders(clientId, conn);

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
			logger.info("ClientDAO: getClientOrders() failed - " + e.getMessage());
			throw new ConnectionNotFoundException("ClientDAO: getClientOrders() failed - " + e.getMessage());
		}
	}

	// Returns a ResultSet of all the Orders place by a single client
	private ResultSet readAllClientOrders(int clientId, Connection conn) throws ConnectionNotFoundException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			logger.info("Exception: " + e1.getMessage());
			throw new ConnectionNotFoundException("Connection to database failed when retrieving orders for the client");
		}
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(SqlQueries.CLIENT_ORDERS_QUERY);
			preparedStatement.setInt(1, clientId);
			
			return preparedStatement.executeQuery();

		} catch (SQLException e) {
			logger.info("ClientDAO: readAllClientOrders() failed - " + e.getMessage());
			throw new ConnectionNotFoundException("ClientDAO: readAllClientOrders() failed - " + e.getMessage());
		}
	}

	public void deleteClient(String email) throws ConnectionNotFoundException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			logger.info("Connection to database failed when deleting an order " + e.getMessage());
			throw new ConnectionNotFoundException("Connection to database failed when deleting an order");
		}
		try (Connection conn = new DBManager().getConnection()) {
			PreparedStatement preparedStatement = conn.prepareStatement(SqlQueries.DELETE_CLIENT);
			preparedStatement.setString(1, email);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			logger.info("Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public boolean changePassword(String email, String oldPassword, String newPassword) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		String password = null;
		try (Connection conn = new DBManager().getConnection()) {
			PreparedStatement preparedStatement = conn.prepareStatement(SqlQueries.CLIENT_QUERY);
			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				password = resultSet.getString("PASSWORD");
			}
		} catch (ConnectionNotFoundException e) {
			System.out.println("Not found");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("SQL e");
			e.printStackTrace();
		}
		if(password.equals(oldPassword))
		{
			try (Connection conn = new DBManager().getConnection()) {
				PreparedStatement preparedStatement = conn.prepareStatement(SqlQueries.CHANGE_PASSWORD);
				preparedStatement.setString(1, newPassword);
				preparedStatement.setString(2, email);
				preparedStatement.executeUpdate();
				return true;
			} catch (ConnectionNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	public boolean enoughBalance(String email, double price)
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		double balance=0;
		try (Connection conn = new DBManager().getConnection()) {
			PreparedStatement preparedStatement = conn.prepareStatement(SqlQueries.CLIENT_QUERY);
			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				balance = resultSet.getDouble("BUDGET");
			}
		} catch (ConnectionNotFoundException e) {
			System.out.println("Not found");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("SQL e");
			e.printStackTrace();
		}
		if(balance<price) {
			return false;
		}
		else
		{
			return true;
		}
	}
}
