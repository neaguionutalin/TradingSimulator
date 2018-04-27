package main.com.m3c.gp.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(OrderDAO.class);
	public void insertOrder(Order order) throws ConnectionNotFoundException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			logger.error("Connection to database failed when trying to insert a new order " + e1.getMessage());
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
			logger.error("OrderDAO: Insert Order details to database failed - " + e.getMessage());
			throw new ConnectionNotFoundException("OrderDAO: Insert Order details to database failed - " + e.getMessage());
		}
	}

	// Retrieves a Order object from the Database table 'Orders'
	public OrderDTO getOrder(int orderId) throws ConnectionNotFoundException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			logger.error("Connection to database failed when retrieving an order " + e1.getMessage());
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
			logger.error(e.getMessage() + e.getStackTrace());
			throw new ConnectionNotFoundException(e.getMessage() + e.getStackTrace());
		}
		return null;
	}

	// Deletes an order (with ID = orderID) in the database 
	public void deleteOrder(int orderID) throws ConnectionNotFoundException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			logger.error("Connection to database failed when deleting an order " + e.getMessage());
			throw new ConnectionNotFoundException("Connection to database failed when deleting an order");
		}
		try (Connection conn = new DBManager().getConnection()) {
			PreparedStatement preparedStatement = conn.prepareStatement(SqlQueries.DELETE_ORDER);
			preparedStatement.setInt(1, orderID);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			logger.error(e.getMessage() + e.getStackTrace());
			throw new ConnectionNotFoundException(e.getMessage() + e.getStackTrace());
		}
	}
	
	public List<Instrument> getInstruments(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try (Connection conn = new DBManager().getConnection()) {
			List<Instrument> instruments = new ArrayList<Instrument>();
			PreparedStatement preparedStatement = conn.prepareStatement(SqlQueries.GET_MARKET);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				instruments.add(new Instrument(resultSet.getString("INSTRUMENT_TICKER"), resultSet.getDouble("PRICE"), resultSet.getString("INSTRUMENT_NAME")));
			}
			return instruments;
		} catch (ConnectionNotFoundException e) {
			System.out.println("Not found");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("SQL e");
			e.printStackTrace();
		}
		return null;
	}
}