package main.com.m3c.gp.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import main.com.m3c.gp.model.OrderInterface;

/**
 * Author: Metin Dagcilar
 * Date: 19/04/18
 * Database Manager
 */

public class DBManager implements DBManagerInterface {

    private static Connection conn = null;

    //TODO: change these to AWS instance prods create
    private static final String USER_NAME = "todowin";
    private static final String PASSWORD = "todopass";
    private static final String HOST_NAME = "mthree-oms-todowin.cbpt8oeqlkno.us-west-2.rds.amazonaws.com";
    private static final String PORT_NUMBER = "1521";
    private static final String SID = "TODOWIN";

    private static final String DB_HOSTNAME = "jdbc:oracle:thin:@//" + HOST_NAME + ":" + PORT_NUMBER + "/" + SID;

    /**
     * @return Get the current connection, if not create a connection
     */
    private static Connection getConnection() {
        if (conn != null) return conn;
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
            System.err.println("Connection to database failed: " + e.getMessage());
        }
        return conn;
    }


	@Override
	public void insertOrder(OrderInterface order) {
        conn = getConnection();

//      String sqlQuery = "INSERT INTO completed_trades" +
//              "(client_id, client_order_id, instrument, quantity, price)" +
//              "VALUES(?,?,?,?,?)";
//
//      try {
//          PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
//
//          preparedStatement.setString(1, client_id);
//          preparedStatement.setString(2, client_order_id);
//          preparedStatement.setString(3, instrument);
//          preparedStatement.setInt(4, quantity);
//          preparedStatement.setDouble(5, price);
//
//          preparedStatement.executeUpdate();
//
//      } catch (SQLException e) {
//          System.err.println("Insert to database failed: " + e.getMessage());
//      }
	}


	@Override
	public OrderInterface getOrder(Integer clientId) {
		// TODO Auto-generated method stub
		return null;
	}

}