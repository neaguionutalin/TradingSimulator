package main.com.m3c.gp.beans;

import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import main.com.m3c.gp.database.ClientDTO;
import main.com.m3c.gp.database.Service;

@Named
@RequestScoped
public class Buy {
	
	private String instrumentTicker;
	private double price;
	private int quantity;
	private String type;
	
	public void doBuy() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		ClientDTO clientDTO = (ClientDTO) session.getAttribute("client");
		int clientID = clientDTO.getClient();
		Service service = new Service();
		service.insertOrder("to be removed", instrumentTicker, clientID, price, quantity, type);		
	}
}