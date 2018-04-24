package main.com.m3c.gp.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.inject.Named;

import java.sql.Connection;

import main.com.m3c.gp.model.Instrument;
import main.com.m3c.gp.model.Order;
import main.com.m3c.gp.model.OrderType;

/**
 * Author: Metin Dagcilar, Ali Saleem Date: 19/04/18 Database Manager interface
 */

@Named
public class OrderDAO {
	// Inserts an Order details into the Database table 'Orders'

	public void insertOrder(Order order) throws ConnectionNotFoundException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			throw new ConnectionNotFoundException("Connection to database failed when trying to insert a new order");
		}
		try (Connection conn = new DBManager().getConnection()) {
			PreparedStatement preparedStatement = conn.prepareStatement(SqlQueries.INSERT_ORDER_QUERY);

			preparedStatement.setString(1, String.valueOf(order.getClientId()));
			preparedStatement.setString(2, order.getInstrument().getName());
			preparedStatement.setString(3, order.getInstrument().getTicker());
			preparedStatement.setDouble(4, order.getPrice());
			preparedStatement.setInt(5, order.getQuantity());
			preparedStatement.setString(6, order.getType().toString());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.err.println("OrderDAO: Insert Order details to database failed - " + e.getMessage());
		}
	}

	// Retrieves a Order object from the Database table 'Orders'
	public OrderDTO getOrder(int orderId) throws ConnectionNotFoundException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			throw new ConnectionNotFoundException("Connection to database failed when retrieving an order");
		}
		try (Connection conn = new DBManager().getConnection()) {
			PreparedStatement preparedStatement = conn.prepareStatement(SqlQueries.ORDER_QUERY);
			preparedStatement.setInt(1, orderId);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				int orderID = resultSet.getInt("ORDER_ID");
				int clientID = resultSet.getInt("CLIENT_ID");
				String instrumentName = resultSet.getString("INSTRUMENT_NAME");
				String instrumentTicker = resultSet.getString("INSTRUMENT_TICKER");
				double price = resultSet.getDouble("PRICE");
				int quantity = resultSet.getInt("QUANTITY");
				String type = resultSet.getString("TYPE");
				Instrument instrument = new Instrument(instrumentTicker, instrumentName);

				OrderType orderType = null;
				if (type.equals("BUY")) {
					orderType = OrderType.BUY;
				} else
					orderType = OrderType.SELL;

				return new OrderDTO(orderID, instrument, clientID, price, quantity, orderType);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void deleteOrder(int orderID) throws ConnectionNotFoundException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new ConnectionNotFoundException("Connection to database failed when deleting an order");
		}
		try (Connection conn = new DBManager().getConnection()) {
			PreparedStatement preparedStatement = conn.prepareStatement(SqlQueries.DELETE_ORDER);
			preparedStatement.setInt(1, orderID);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}