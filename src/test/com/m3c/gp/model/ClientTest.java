/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package test.com.m3c.gp.model;

import main.com.m3c.gp.model.Client;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClientTest {

    Client client;

    @Before
    public void before(){
        client = new Client("Alin", "Neagu", "neagu_ionutalin@icloud.com", "Pa55w0rd");
    }


    @Test
    public void testGetFirstName() {
        String firstName=client.getFirstName();
        Assert.assertEquals(firstName, "Alin");
    }

    @Test
    public void testGetLastName() {
        Assert.assertEquals(client.getLastName(), "Neagu");
    }

    @Test
    public void testGetEmail() {
        Assert.assertEquals(client.getEmail(), "neagu_ionutalin@icloud.com");
    }

    @Test
    public void testGetPassword() {
        Assert.assertEquals(client.getPassword(), "Pa55w0rd");
    }

    @Test
    public void testUpdateLastName() {
        client.updateLastName("Smith");
        Assert.assertEquals(client.getLastName(), "Smith");
    }

    @Test
    public void testUpdateFirstName() {
        client.updateFirstName("John");
        Assert.assertEquals(client.getFirstName(), "John");
    }

    @Test
    public void testUpdateEmail() {
        client.updateEmail("john.smith@icloud.com");
        Assert.assertEquals(client.getEmail(), "john.smith@icloud.com");
    }

    @Test
    public void testUpdatePassword() {
        client.updatePassword("pass");
        Assert.assertEquals(client.getPassword(), "pass");
    }
}
