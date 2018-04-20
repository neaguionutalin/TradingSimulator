package main.com.m3c.gp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import main.com.m3c.gp.model.Client;
import main.com.m3c.gp.model.Instrument;
import main.com.m3c.gp.model.Order;
import main.com.m3c.gp.model.OrderType;

/**
 * Author: Metin Dagcilar Date: 19/04/18 Database Manager
 */

public class DBManager implements DBManagerInterface {

	private static Connection conn = null;

	// TODO: change these to AWS instance prods create
	private static final String USER_NAME = "ec2-user";
	private static final String PASSWORD = "Pa55w0rd";
	private static final String PUBLIC_DNS = "18.217.124.114";
	private static final String DATABASE = "ip-172-31-22-53.us-east-2.compute.internal";
	private static final String PORT_NUMBER = "3306";

	private static final String DB_HOSTNAME = "jdbc:mysql://" + PUBLIC_DNS + ":" + PORT_NUMBER + "/" + DATABASE;

	/**
	 * Get the current connection, if not create a connection
	 * 
	 * @return Connection
	 */
	public static Connection getConnection() {
		if (conn != null)
			return conn;
		return connect();
	}

	/**
	 * Initialise connection to database
	 *
	 * @return Connection
	 */
	private static Connection connect() {
		try {
			conn = DriverManager.getConnection(DB_HOSTNAME, USER_NAME, PASSWORD);
		} catch (Exception e) {
			System.err.println("DBManager: Connection to database failed - " + e.getMessage());
		}
		return conn;
	}

	// Close the connection to the database
	private void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				System.err.println("DBManager: Closing database connection failed - " + e.getMessage());
			}
		}
	}

	// Inserts an Order details into the Database table 'Orders'
	@Override
	public void insertOrder(Order order) {
		conn = getConnection();

		String sqlQuery = "INSERT INTO Orders" + "(order_id, client_id, instrument_name, price, quantity, type)"
				+ "VALUES(?,?,?,?,?,?)";

		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);

			preparedStatement.setString(1, String.valueOf(order.getOrderId()));
			preparedStatement.setString(2, String.valueOf(order.getClientId()));
			preparedStatement.setString(3, order.getInstrument().getTicker().toString());
			preparedStatement.setDouble(4, order.getPrice());
			preparedStatement.setInt(5, order.getQuantity());
			preparedStatement.setString(6, order.getType().toString());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.err.println("DBManager: Insert Order details to database failed - " + e.getMessage());
		}
	}

	// Inserts a Client's details into the Database table 'Clients'

	@Override
	public void insertClient(Client client) {
		conn = getConnection();

		String sqlQuery = "INSERT INTO Clients"
				+ "(client_id, first_name, last_name, email, password, user_group, budget)" + "VALUES(?,?,?,?,?,?,?)";

		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);

			preparedStatement.setString(1, String.valueOf(client.getClientId()));
			preparedStatement.setString(2, client.getFirstName());
			preparedStatement.setString(3, client.getLastName());
			preparedStatement.setString(4, client.getEmail());
			preparedStatement.setString(5, client.getPassword());
			preparedStatement.setDouble(6, client.getclientBalance());
			preparedStatement.setString(7, client.getUserGroup().toString());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.err.println("DBManager: Insert Client details to database failed - " + e.getMessage());
		}

	}

	// Retrieves a Order object from the Database table 'Orders'
	@Override
	public Order getOrder(int orderId) {
		conn = getConnection();

		return null;
	}

	// Retrieves a Client object from the Database table 'Clients'

	@Override
	public Client getClient(int clientId) {
		conn = getConnection();

		return null;
	}

	// Returns a List of Orders for a given clientId from the 'Orders' table
	@Override
	public List<Order> getClientOrders(int clientId) {
		ResultSet resultSet = readAllClientOrders(clientId);
		List<Order> clientOrders = null;

		return clientOrders;
	}

	// Returns a ResultSet of all the Orders place by a single client
	private ResultSet readAllClientOrders(int clientId) {
		conn = getConnection();

		String sqlQuery = "SELECT * FROM Orders WHERE client_id" + "VALUES(?)";

		try {
			Statement statement = conn.createStatement();
			return statement.executeQuery(sqlQuery);

		} catch (SQLException e) {
			System.out.println("DBManager: readAllClientOrders() failed - " + e.getMessage());
		}
		return null;
	}

	// If email exists in the database table 'Clients' return true
	@Override
	public boolean emailExists(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	// if email and password match return true
	@Override
	public boolean emailPasswordMatch(String email, String password) {
		// TODO Auto-generated method stub
		return false;
	}
}