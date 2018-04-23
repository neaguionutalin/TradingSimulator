package test.com.m3c.gp.controller;

import main.com.m3c.gp.database.DBManager;
import main.com.m3c.gp.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

/**
 * Author: Metin Dagcilar & Ionut Alin Neagu
 * Date: 19/04/18
 * DBManager test
 */

public class DBManagerTest {

	@Test
	public void testInsertReturnOrder() {
		DBManager dbManager = new DBManager();
		Order order = new Order(1, new Instrument("VOD", "Vodafone") , 1, 50,20, OrderType.BUY);
		dbManager.insertOrder(order);
		Order order1 = dbManager.getOrder(1);
		Assert.assertEquals(order,order1);
	}

	@Test
	public void testInsertReturnCustomer() {
		DBManager dbManager = new DBManager();
		Client client = new Client(1, "Alin", "Neagu", "neagu_ionutalin@icloud.com", "Pa55w0rd", 10000);
		dbManager.insertClient(client);
		Client client1 = dbManager.getClient(1);
		Assert.assertEquals(client,client1);
	}

	@Test
	public void testReturnOrders(){
		DBManager dbManager = new DBManager();
		List<Order> orders = null;
		orders.set(1, new Order(1, new Instrument("VOD", "Vodafone"), 1, 50, 20, OrderType.BUY));
		dbManager.insertOrder(orders.get(1));
		orders.set(2, new Order(2, new Instrument("BP", "BP") , 1, 30,50, OrderType.BUY));
		dbManager.insertOrder(orders.get(2));
		Assert.assertThat(orders, is(dbManager.getClientOrders(1)));
	}

	@Test
	public void testEmailExists(){
		DBManager dbManager = new DBManager();
		String email = "neagu_ionutalin@icloud.com";
		Assert.assertEquals(dbManager.emailExists(email),true);
	}

	@Test
	public void testEmailPasswordMatch(){
		DBManager dbManager = new DBManager();
		Assert.assertEquals(dbManager.emailPasswordMatch("neagu_ionutalin@icloud.com", "Pa55w0rd"), true);
	}

	@Test
	public void testConnection(){
		DBManager dbManager = new DBManager();
		Assert.assertNotEquals(dbManager.getConnection(),null);
	}

}
