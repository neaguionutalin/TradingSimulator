package main.com.m3c.gp.beans;

import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
public class Controller {

	public boolean sessionValues() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		if (session == null) {
			//TODO: call login page
		} else {
			//TODO: set user values
		}
	}
	
	
}
