package main.com.m3c.gp.model;

public class Client implements ClientInterface {
	private int clientId;
	private double budget;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private UserGroup userGroup;

	// Created by front-end
	public Client(String firstname, String lastname, String email, String pass) {
		this.clientId = -1;
		this.budget = 10000;
		this.firstName = firstname;
		this.lastName = lastname;
		this.email = email;
		this.password = pass;
		this.userGroup = UserGroup.USER;
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
		this.lastName = lastname;
	}

	@Override
	public void updateFirstName(String firstname) {
		this.firstName = firstname;
	}

	@Override
	public void updateEmail(String emails) {
		this.email = emails;
	}

	@Override
	public void updatePassword(String pass) {
		this.password = pass;
	}

	@Override
	public void setClientBalance(double balance) {
		budget = balance;
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
