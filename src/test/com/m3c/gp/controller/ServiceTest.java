/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package test.com.m3c.gp.controller;

import main.com.m3c.gp.database.ClientDTO;
import main.com.m3c.gp.database.OrderDTO;
import main.com.m3c.gp.database.Service;
import org.junit.Test;

public class ServiceTest {

    @Test
    public void getUserTest(){
        Service service = new Service();
        ClientDTO client = service.getClient("neagu_ionutalin@icloud.com");
        System.out.println(client.getLastname());
    }

    @Test
    public void getOrderTest(){
        Service service = new Service();
        OrderDTO order = service.getOrder(1);
        System.out.println(order.getPrice());

    }

}