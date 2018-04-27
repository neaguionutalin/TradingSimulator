package main.com.m3c.gp.database;

interface SqlQueries {

	final String INSERT_ORDER_QUERY = "INSERT INTO Trading_Simulator.Orders"
			+ "(client_id, instrument_name, instrument_ticker, price, quantity, type)" + "VALUES(?,?,?,?,?,?)";

	final String INSERT_CLIENT_QUERY = "INSERT INTO Trading_Simulator.Clients"
			+ "(first_name, last_name, email, password, user_group, budget)" + "VALUES(?,?,?,?,?,?)";

	final String CLIENT_ORDERS_QUERY = "SELECT * FROM Trading_Simulator.Orders WHERE client_id=?";

	final String EMAIL_EXISTS_QUERY = "SELECT * FROM Trading_Simulator.Clients WHERE email=?";

	final String MATCH_EMAIL_PASSWORD_QUERY = "SELECT * FROM Trading_Simulator.Clients WHERE email=? and password=?";

	final String CLIENT_QUERY = "SELECT * FROM Trading_Simulator.Clients WHERE email=?";

	final String CLIENT_QUERY_ID = "SELECT * FROM Trading_Simulator.Clients WHERE client_id=?";

	final String ORDER_QUERY = "SELECT * FROM Trading_Simulator.Orders WHERE order_id=?";

	final String DELETE_ORDER = "DELETE FROM Trading_Simulator.Orders WHERE order_id=?";

	final String DELETE_CLIENT = "DELETE FROM Trading_Simulator.Clients WHERE email=?";

	final String CHANGE_PASSWORD = "UPDATE Trading_Simulator.Clients SET password=? WHERE email=?";
	
	final String CHANGE_BALANCE = "UPDATE Trading_Simulator.Clients SET BUDGET=? WHERE email=?";
	
	final String GET_MARKET = "SELECT * FROM Instruments";
}
