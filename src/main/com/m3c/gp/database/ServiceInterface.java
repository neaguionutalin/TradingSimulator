package main.com.m3c.gp.database;

import java.util.List;

import main.com.m3c.gp.model.Client;
import main.com.m3c.gp.model.Order;

/**
 * Author: Metin Dagcilar, Ali Saleem
 * Date: 19/04/18
 * Database Manager interface
 */

public interface ServiceInterface{
	void insertOrder(String instrumentName, String instrumentTicket, int clientId, double price, int quantity, String type);
	OrderDTO getOrder(int clientId);
	List<OrderDTO> getClientOrders(int clientId);
	
	void insertClient(String firstname, String lastname, String email, String pass);
	ClientDTO getClient(String email);
	
	boolean emailExists(String email);
	boolean emailPasswordMatch(String email, String password);
}