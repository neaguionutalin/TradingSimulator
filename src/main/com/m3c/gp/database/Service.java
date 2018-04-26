package main.com.m3c.gp.database;

import java.util.List;

import javax.inject.Inject;

import main.com.m3c.gp.model.Client;
import main.com.m3c.gp.model.Instrument;
import main.com.m3c.gp.model.Order;
import main.com.m3c.gp.model.OrderType;

public class Service implements ServiceInterface {
	@Inject
	private OrderDAO orderDAO;
	@Inject
	private ClientDAO clientDAO;
	@Inject
	private AuthenticationDAO authenticationDAO;
	
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Service.class);
	public Service() {
		orderDAO = new OrderDAO();
		clientDAO = new ClientDAO();
		authenticationDAO = new AuthenticationDAO();
	}

	@Override
	public void insertOrder(String instrumentName, String instrumentTicker, int clientId, double price, int quantity,
			String type) {

		OrderType orderType = null;
		if (type.equals("BUY")) {
			orderType = OrderType.BUY;
		} else
			orderType = OrderType.SELL;

		try {
			orderDAO.insertOrder(
					new Order(new Instrument(instrumentTicker, instrumentName), clientId, price, quantity, orderType));
		} catch (ConnectionNotFoundException e) {
			logger.error("Exception: " + e.getMessage());
			e.getMessage();
		}

	}

	// Inserts a Client's details into the Database table 'Clients'
	@Override
	public void insertClient(String firstname, String lastname, String email, String pass) {
		try {
			clientDAO.insertClient(new Client(firstname, lastname, email, pass));
		} catch (ConnectionNotFoundException e) {
			logger.error("Exception: " + e.getMessage());
			e.getMessage();
		}
	}

	// Retrieves a Order object from the Database table 'Orders'
	public OrderDTO getOrder(int orderId) {
			try {
				return orderDAO.getOrder(orderId);
			} catch (ConnectionNotFoundException e) {
				logger.error("Exception: " + e.getMessage());
				e.getMessage();
			}
		return null;
	}

	// Retrieves a Client object from the Database table 'Clients'
	@Override
	public ClientDTO getClient(String email) {
			try {
				return clientDAO.getClient(email);
			} catch (ConnectionNotFoundException e) {
				logger.error("Exception: " + e.getMessage());
				e.getMessage();
			}
		return null;
	}

	// Returns a List of Orders for a given clientId from the 'Orders' table
	@Override
	public List<OrderDTO> getClientOrders(int clientId) {
			try {
				return clientDAO.getClientOrders(clientId);
			} catch (ConnectionNotFoundException e) {
				logger.error("Exception: " + e.getMessage());
				e.getMessage();
			}
		return null;
	}

	@Override
	// If email exists in the database table 'Clients' return true
	public boolean emailExists(String email) {
			try {
				return authenticationDAO.emailExists(email);
			} catch (ConnectionNotFoundException e) {
				logger.error("Exception: " + e.getMessage());
				e.getMessage();
			}
		return false;
	}

	@Override
	// if email and password match return true
	public boolean emailPasswordMatch(String email, String password) {
			try {
				return authenticationDAO.emailPasswordMatch(email, password);
			} catch (ConnectionNotFoundException e) {
				logger.error("Exception: " + e.getMessage());
				e.getMessage();
			}
		return false;
	}

	@Override
	public void deleteOrder(int orderID) {
		try {
			orderDAO.deleteOrder(orderID);
		} catch (ConnectionNotFoundException e) {
			logger.error("Exception: " + e.getMessage());
			e.getMessage();
		}
	}

	@Override
	public void deleteClient(String email) {
		try {
			clientDAO.deleteClient(email);
		} catch (ConnectionNotFoundException e) {
			logger.error("Exception: " + e.getMessage());
			e.getMessage();
		}
	}

	@Override
	public boolean updatePassword(String email, String oldPassword, String newPassword) {
		return clientDAO.changePassword(email, oldPassword, newPassword);
	}
	
	@Override
	public boolean enoughBalance(String email, double price) {
		return clientDAO.enoughBalance(email, price);
	}
	
}