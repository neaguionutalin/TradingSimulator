package main.com.m3c.gp.beans;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import main.com.m3c.gp.database.ClientDTO;

public class Personalise {

	public String getFullName() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		ClientDTO clientDTO = (ClientDTO) session.getAttribute("client");
		String firstName = clientDTO.getFirstname();
		String lastName = clientDTO.getLastname();
		return firstName + " " + lastName;
	}
	
	public double getBalance() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		ClientDTO clientDTO = (ClientDTO) session.getAttribute("client");
		return clientDTO.getCustomerBudget();
	}
}
