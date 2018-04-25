package main.com.m3c.gp.beans;

import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import main.com.m3c.gp.database.ClientDTO;
import main.com.m3c.gp.database.OrderDTO;
import main.com.m3c.gp.database.Service;

public class Portfolio {
	
	public List<OrderDTO> getOrders() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		ClientDTO clientDTO = (ClientDTO) session.getAttribute("client");
		int clientID = clientDTO.getClient();
		Service service = new Service();
		List<OrderDTO> orders = service.getClientOrders(clientID);
		return orders;
	}
}
