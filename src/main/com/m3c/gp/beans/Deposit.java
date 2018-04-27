package main.com.m3c.gp.beans;

import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import main.com.m3c.gp.database.ClientDTO;
import main.com.m3c.gp.database.Service;

@Named
public class Deposit {

	private double amount;

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public String doDeposit() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		ClientDTO clientDTO = (ClientDTO) session.getAttribute("client");
		Service service = new Service();
		String email = clientDTO.getEmail();
		service.addBalance(email, amount);
		ClientDTO newClientDTO = service.getClient(email);
		session.setAttribute("client", newClientDTO);
		return "dashboard?faces-redirect=true";
	}
	
}
