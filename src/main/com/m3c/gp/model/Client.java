package main.com.m3c.gp.model;

public class Client implements ClientInterface {
    private int clientId;
    private double budget;

    public Client(int clientid, double custumerBugdet)
    {
        clientId=clientid;
        budget=custumerBugdet;
    }

    @Override
    public int getClientId() {
        return clientId;
    }

    @Override
    public void sendClientOrder(Object o) {

    }

    @Override
    public void setClientBalance(double balance) {
        budget=balance;
    }

    @Override
    public double getclientBalance(int clientId) {
        return budget;
    }
}
