package main.com.m3c.gp.beans;

import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import main.com.m3c.gp.database.ClientDTO;
import main.com.m3c.gp.database.OrderDTO;
import main.com.m3c.gp.database.Service;

public class Portfolio {
	
	public String[] getOrders() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		ClientDTO clientDTO = (ClientDTO) session.getAttribute("client");
		int clientID = clientDTO.getClient();
		Service service = new Service();
		List<OrderDTO> orders = service.getClientOrders(clientID);
		String[] orderStrings = new String[orders.size()];
		int strCnt = 0;
		for (OrderDTO orderDTO : orders) {
			double total = orderDTO.getQuantity()*orderDTO.getPrice();
			String orderString = orderDTO.getType() + " " + orderDTO.getQuantity() + " " + orderDTO.getInstrument().getTicker() + " GBP" + orderDTO.getPrice()+" per Unit Total GBP" + total;
			orderStrings[strCnt++] = orderString;
		}
		return orderStrings;
	}
}
