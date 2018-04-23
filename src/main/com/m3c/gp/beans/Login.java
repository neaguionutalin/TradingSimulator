package main.com.m3c.gp.beans;
import java.io.IOException;

import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

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
//		if (emailExists(email) && emailPasswordMatch(email, passs)) {
//			return "dashboard.xhtml";
//		} else if (emailExists(email))
//		return null; 
		return "dashboard.xhtml";
	}
	
}
