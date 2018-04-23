package main.com.m3c.gp.beans;

import javax.faces.bean.RequestScoped;
import javax.inject.Named;

import main.com.m3c.gp.database.Service;

@Named
@RequestScoped
public class Login {
	
	private String email;
	private String pass;
	
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
	
	public String doLogin() {
		Service service = new Service();
		if (service.emailPasswordMatch(email, pass)) {
			return "dashboard.xhtml";
		} else {
		return null;
		}
	}
}
