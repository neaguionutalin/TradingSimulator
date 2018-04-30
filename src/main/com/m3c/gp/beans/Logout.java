package main.com.m3c.gp.beans;

import javax.faces.context.FacesContext;

public class Logout {

	
	public String doLogout(){
		
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		
		return "index?faces-redirect=true";
	}
	
	
}
