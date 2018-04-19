package com.m3c.gp.model;

public interface Client {
    int getClientId();
    void sendClientOrder(Object o);
    void setClientBalance(double balance);
    double getclientBalance(int clientId);
    void cancelClientOrder(Object o);
}
