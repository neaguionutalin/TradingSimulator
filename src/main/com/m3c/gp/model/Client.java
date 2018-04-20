package main.com.m3c.gp.model;

public class Client implements ClientInterface {
    private int clientId;
    private double budget;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public Client(int clientid, String firstname, String lastname, String emails, String pass, double costumerBudget)
    {
        clientId=clientid;
        budget=costumerBudget;
        firstName=firstname;
        lastName=lastname;
        email=emails;
        password=pass;
    }

    @Override
    public int getClientId() {
        return clientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void updateLastName(String lastname) {
        lastName=lastname;
    }

    public void updateFirstName(String firstname){
        firstName=firstname;
    }

    public void updateEmail(String emails){
        email=emails;
    }
    public void updatePassword(String pass){
        password=pass;
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
