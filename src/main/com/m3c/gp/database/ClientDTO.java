package main.com.m3c.gp.database;

import main.com.m3c.gp.model.UserGroup;

public class ClientDTO {
	
	private int client;
	private String firstname;
	private String lastname;
	private String email;
	private String pass;
	private double customerBudget;
	private UserGroup userGroup;

	public ClientDTO(int clientid, String firstname, String lastname, String email, String pass, double customerBudget, UserGroup userGroup) {
		this.client = clientid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.pass = pass;
		this.customerBudget = customerBudget;
		this.userGroup = userGroup;
	}

	public int getClient() {
		return client;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getEmail() {
		return email;
	}

	public String getPass() {
		return pass;
	}

	public double getCustomerBudget() {
		return customerBudget;
	}

	public UserGroup getUserGroup() {
		return userGroup;
	}
}
