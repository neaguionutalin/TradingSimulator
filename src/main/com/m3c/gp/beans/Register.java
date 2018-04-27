package main.com.m3c.gp.beans;

import main.com.m3c.gp.database.Service;

public class Register {

	private String firstName;
	private String lastName; 
	private String email;
	private String pass;
	private String msg = "";
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPass() {
		return pass;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public String doRegister() {
		Service service = new Service();
		if (! service.emailExists(email)) {
			service.insertClient(firstName, lastName, email, pass);
			return "dashboard?faces-redirect=true";
		} else {
			setMsg("Email already exists");
			return null;
		}
		
	}
}
