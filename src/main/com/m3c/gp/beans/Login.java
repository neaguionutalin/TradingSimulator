package main.com.m3c.gp.beans;

import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import main.com.m3c.gp.database.ClientDTO;
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
			ClientDTO clientDTO = service.getClient(email);
			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
			session.setAttribute("client", clientDTO);
			return "dashboard.xhtml";
		} else {
		return null;
		}
	}
}
