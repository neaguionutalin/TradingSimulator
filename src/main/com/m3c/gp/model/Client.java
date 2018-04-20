package main.com.m3c.gp.model;

public class Client implements ClientInterface {
    private int clientId;
    private double budget;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private UserGroup userGroup;

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
        return this.clientId;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public void updateLastName(String lastname) {
        this.lastName=lastname;
    }

    @Override
    public void updateFirstName(String firstname){
        this.firstName=firstname;
    }

    @Override
    public void updateEmail(String emails){
        this.email=emails;
    }

    @Override
    public void updatePassword(String pass){
        this.password=pass;
    }

    @Override
    public void sendClientOrder(Object o) {

    }

    @Override
    public void setClientBalance(double balance) {
        budget=balance;
    }

    @Override
    public double getclientBalance() {
        return this.budget;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }
}
