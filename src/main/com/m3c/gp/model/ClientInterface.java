/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package main.com.m3c.gp.model;

public interface ClientInterface {
    int getClientId();
    void sendClientOrder(Object o);
    void setClientBalance(double balance);
    double getclientBalance(int clientId);
    String getFirstName();
    String getLastName();
    String getEmail();
    String getPassword();
    void updateLastName(String lastname);
    void updateFirstName(String firstname);
    void updateEmail(String emails);
    void updatePassword(String pass);
}
