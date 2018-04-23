package main.com.m3c.gp.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.inject.Named;

import java.sql.Connection;

import main.com.m3c.gp.model.Order;

/**
 * Author: Metin Dagcilar, Ali Saleem 
 * Date: 19/04/18 
 * Database Manager interface
 */

@Named
public class OrderDAO {
	// Inserts an Order details into the Database table 'Orders'

	public void insertOrder(Order order) {
		
		try (Connection conn = DBManager.getConnection()){
			PreparedStatement preparedStatement = conn.prepareStatement(SqlQueries.INSERT_ORDER_QUERY);

			// to auto-increment
			// preparedStatement.setString(1, String.valueOf(order.getOrderId())); Get this

			preparedStatement.setString(2, String.valueOf(order.getClientId()));
			preparedStatement.setString(3, order.getInstrument().getTicker().toString());
			preparedStatement.setString(4, order.getInstrument().getName());
			preparedStatement.setDouble(5, order.getPrice());
			preparedStatement.setInt(6, order.getQuantity());
			preparedStatement.setString(7, order.getType().toString());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.err.println("OrderDAO: Insert Order details to database failed - " + e.getMessage());
		}
	}

	// Retrieves a Order object from the Database table 'Orders'
	public Order getOrder(int orderId) {
		Connection conn = DBManager.getConnection();

		return null;
	}

}
