package main.com.m3c.gp.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;


import main.com.m3c.gp.model.Client;
import main.com.m3c.gp.model.Instrument;
import main.com.m3c.gp.model.Order;
import main.com.m3c.gp.model.OrderType;

public class Service implements ServiceInterface{
	@Inject
	private OrderDAO orderDAO;
	@Inject
	private ClientDAO clientDAO;
	@Inject
	private AuthenticationDAO authenticationDAO;
	
	public Service() {
		orderDAO = new OrderDAO();
		clientDAO = new ClientDAO();
		authenticationDAO = new AuthenticationDAO();
	}

	@Override
	public void insertOrder(String instrumentName, String instrumentTicker, int clientId, double price, int quantity, String type) {
		
		OrderType orderType = null;
		if (type == "BUY") {
			orderType = OrderType.BUY;
		} else {
			orderType = OrderType.SELL;
		}
		
		orderDAO.insertOrder(new Order(new Instrument(instrumentTicker, instrumentName), clientId, price, quantity, orderType));
	}

	// Inserts a Client's details into the Database table 'Clients'
	@Override
	public void insertClient(String firstname, String lastname, String email, String pass) {
		clientDAO.insertClient(new Client(firstname, lastname, email, pass));
	}

	// Retrieves a Order object from the Database table 'Orders'
	public  OrderDTO getOrder(int orderId) {
		return orderDAO.getOrder(orderId);
	}

	// Retrieves a Client object from the Database table 'Clients'
	@Override
	public ClientDTO getClient(String email) {
		return clientDAO.getClient(email);
	}

	// Returns a List of Orders for a given clientId from the 'Orders' table
	@Override
	public List<OrderDTO> getClientOrders(int clientId) {
		return clientDAO.getClientOrders(clientId);
	}

	@Override
	// If email exists in the database table 'Clients' return true
	public boolean emailExists(String email) {
		return authenticationDAO.emailExists(email);
	}

	@Override
	// if email and password match return true
	public boolean emailPasswordMatch(String email, String password) {
		return authenticationDAO.emailPasswordMatch(email, password);
	}
}