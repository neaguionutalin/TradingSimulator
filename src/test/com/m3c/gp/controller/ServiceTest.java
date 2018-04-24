package test.com.m3c.gp.controller;

import main.com.m3c.gp.database.ClientDTO;
import main.com.m3c.gp.database.OrderDTO;
import main.com.m3c.gp.database.Service;
import main.com.m3c.gp.model.OrderType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class ServiceTest {

    @Test
    public void getUserTest(){
        Service service = new Service();
        ClientDTO client = service.getClient("neagu_ionutalin@icloud.com");
        assertEquals("neagu_ionutalin@icloud.com", client.getEmail());
    }

    @Test
    public void getOrderTest(){
        Service service = new Service();
        OrderDTO order = service.getOrder(1);
        
        assertEquals(1,  order.getClientId());
        assertEquals("VODAFONE",  order.getInstrument().getName());
        assertEquals("VOD",  order.getInstrument().getTicker());
        assertEquals(20.0000,  order.getPrice(), 1);
        assertEquals(50,  order.getQuantity());
        assertEquals(OrderType.BUY,  order.getType());
    }
    
    @Test
    public void emailExistsAuth() {
    	Service service = new Service();
    	String email = "neagu_ionutalin@icloud.com";
    	assertTrue(service.emailExists(email));
    }
    
    @Test
    public void emailNotExistsAuth() {
    	Service service = new Service();
    	String email = "iamlegend@willsmith.com"; 
    	assertFalse(service.emailExists(email));
    }

}