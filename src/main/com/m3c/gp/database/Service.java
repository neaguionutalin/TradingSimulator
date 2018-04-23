package main.com.m3c.gp.database;

import java.util.List;
import java.sql.Connection;

import main.com.m3c.gp.model.Client;
import main.com.m3c.gp.model.Instrument;
import main.com.m3c.gp.model.Order;
import main.com.m3c.gp.model.OrderType;

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

	public static Client getClient(int clientId) {
		conn = DBManager.getConnection();

		return null;
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
