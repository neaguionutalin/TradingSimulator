package main.com.m3c.gp.database;

interface SqlQueries {

	final String INSERT_ORDER_QUERY = "INSERT INTO Orders" + "(order_id, client_id, instrument_name, price, quantity, type)"
			+ "VALUES(?,?,?,?,?,?)";
	
	final String INSERT_CLIENT_QUERY = "INSERT INTO Clients"
			+ "(client_id, first_name, last_name, email, password, user_group, budget)" + "VALUES(?,?,?,?,?,?,?)";

	final String CLIENT_ORDERS_QUERY = "SELECT * FROM Orders WHERE client_id" + "VALUES(?)";
}
