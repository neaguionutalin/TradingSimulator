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
		
		try {
			orderDAO.insertOrder(new Order(new Instrument(instrumentTicker, instrumentName), clientId, price, quantity, orderType));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Inserts a Client's details into the Database table 'Clients'
	@Override
	public void insertClient(String firstname, String lastname, String email, String pass) {
		try {
			clientDAO.insertClient(new Client(firstname, lastname, email, pass));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Retrieves a Order object from the Database table 'Orders'
	public  OrderDTO getOrder(int orderId) {
		try {
			return orderDAO.getOrder(orderId);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// Retrieves a Client object from the Database table 'Clients'
	@Override
	public ClientDTO getClient(String email) {
		try {
			return clientDAO.getClient(email);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// Returns a List of Orders for a given clientId from the 'Orders' table
	@Override
	public List<OrderDTO> getClientOrders(int clientId) {
		try {
			return clientDAO.getClientOrders(clientId);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	// If email exists in the database table 'Clients' return true
	public boolean emailExists(String email) {
		try {
			return authenticationDAO.emailExists(email);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	// if email and password match return true
	public boolean emailPasswordMatch(String email, String password) {
		try {
			return authenticationDAO.emailPasswordMatch(email, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}