package test.com.m3c.gp.controller;

import main.com.m3c.gp.database.ClientDTO;
import main.com.m3c.gp.database.OrderDTO;
import main.com.m3c.gp.database.Service;
import main.com.m3c.gp.model.OrderType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ServiceTest {

	private Service service;
	private Random random;

	@Before
	public void setup() {
		service = new Service();
		random = new Random();
	}

	// Gets a ClientDTO from the Database and checks whether the correct values are
	// being returned
	@Test
	public void getClientTest() {
		ClientDTO client = service.getClient("neagu_ionutalin@icloud.com");

		assertEquals("Alin", client.getFirstname());
		assertEquals("Neagu", client.getLastname());
		assertEquals("neagu_ionutalin@icloud.com", client.getEmail());
	}

	// Tries to retrieve a Client that does not exist
	@Test(expected = NullPointerException.class)
	public void clintNotExistTest() {
		ClientDTO client = service.getClient("thisdoesnotexist@hotmail.com");

		client.getEmail(); // this should throw a NullPointerException
	}

	// Tests the emailExists method against a email that does exist
	@Test
	public void emailExistsAuth() {
		assertTrue(service.emailExists("neagu_ionutalin@icloud.com"));
	}

	// Tests the emailExists method against a email that does not exist
	@Test
	public void emailNotExistsAuth() {
		assertFalse(service.emailExists("iamlegend@willsmith.com"));
	}

	// Tests retrieving an Order from the Database
	@Test
	public void getOrderTest() {
		OrderDTO order = service.getOrder(2);

		assertEquals(3, order.getClientId());
		assertEquals("BT Group", order.getInstrument().getName());
		assertEquals("BT", order.getInstrument().getTicker());
		assertEquals(30.0, order.getPrice(), 1);
		assertEquals(500, order.getQuantity());
		assertEquals(OrderType.SELL, order.getType());
	}

	// Create a new client
	// Adds some orders for that client to the DB
	// return all the orders for that client from the DB
	// Asserts the number of orders added with the number of orders found in the DB
	// deletes the orders and client
	@Test
	public void getAllClientOrders() {
		String email = "devtestuser@hotmail.com";
		
		service.insertClient("devtestuser", "test", email, "devpass");
		
		int clientId = service.getClient(email).getClient();
		service.insertOrder("VODAFONE", "VOD", clientId, random.nextInt(200), random.nextInt(1000), "BUY");
		service.insertOrder("Apple", "APPL", clientId, random.nextInt(200), random.nextInt(1000), "BUY");
		service.insertOrder("VODAFONE", "VOD", clientId, random.nextInt(200), random.nextInt(1000), "BUY");
		service.insertOrder("BT Group", "BT", clientId, random.nextInt(200), random.nextInt(1000), "BUY");
		
		List<OrderDTO> clientOrders = service.getClientOrders(clientId);
		
		assertEquals(4, clientOrders.size());
		
		
		for(OrderDTO orderDTO : clientOrders) {
			service.deleteOrder(orderDTO.getOrderId());
		}
		
		service.deleteClient(email);
	}

	// Inserts a client to the Database.
	// Then checks if the user exists
	// Deletes the user
	// checks user has been deleted
	@Test
	public void insertAndDeleteClientTest() {
		service.insertClient("devuser3", "test", "devuser3@hotmail.com", "devpass");

		ClientDTO clientDTO = service.getClient("devuser3@hotmail.com");

		assertEquals("devuser3", clientDTO.getFirstname());
		assertEquals("test", clientDTO.getLastname());
		assertEquals("devuser3@hotmail.com", clientDTO.getEmail());

		service.deleteClient("devuser3@hotmail.com");
		assertFalse(service.emailExists("devuser3@hotmail.com"));
	}

	@Test 
	public void updatePasswordTest(){
		Boolean check = service.updatePassword("neagu_ionutalin@icloud.com", "Pa55w0rd", "Pass");
		Assert.assertEquals(check, true);
		check = service.updatePassword("neagu_ionutalin@icloud.com", "Pass", "Pa55w0rd");
	}
	
	@Test
	public void enoughBalanceTest() {
		Boolean check  = service.enoughBalance("neagu_ionutalin@icloud.com", 5000);
		Assert.assertEquals(true, check);
	}
	
	@Test
	public void notEnoughBalanceTest() {
		Boolean check  = service.enoughBalance("neagu_ionutalin@icloud.com", 9999999);
		Assert.assertNotEquals(true, check);
	}
}