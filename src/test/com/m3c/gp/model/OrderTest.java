package test.com.m3c.gp.model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import main.com.m3c.gp.model.Instrument;
import main.com.m3c.gp.model.Order;
import main.com.m3c.gp.model.OrderType;

public class OrderTest {
	
	Order order; 
	Instrument instrument;
	
	@Before
	public void before() {
		instrument = new Instrument("VOD", "Vodafone");
		order = new Order(instrument, 1, 29.46, 100, OrderType.BUY);
	}

	@Test
	public void testOrderId() {
		assertEquals(1, order.getOrderId());
	}
	
	@Test
	public void testInstrument() {
		assertEquals(instrument, order.getInstrument());
	}
	
	@Test
	public void testClientOrderId() {
		assertEquals(1, order.getClientId());
	}
	
	@Test
	public void testPrice() {
		assertEquals(29.46, order.getPrice(), 1e-15);
	}
	
	@Test
	public void testQuantity() {
		assertEquals(100, order.getQuantity());
	}
	
	@Test
	public void testOrderType() {
		assertEquals(OrderType.BUY, order.getType());
	}
}
