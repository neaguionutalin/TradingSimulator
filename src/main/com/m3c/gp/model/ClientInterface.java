package main.com.m3c.gp.model;

public interface ClientInterface {
    int getClientId();
    void sendClientOrder(Object o);
    void setClientBalance(double balance);
    double getclientBalance(int clientId);
}
