package main.com.m3c.gp.beans;

import javax.faces.bean.RequestScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import main.com.m3c.gp.database.ClientDAO;
import main.com.m3c.gp.database.ClientDTO;
import main.com.m3c.gp.database.Service;

@Named
@RequestScoped
public class Order {

	private String instrumentName;
	private String instrumentTicker;
	private double price;
	private int quantity;
	private String type;

	public String getInstrumentName() {
		return instrumentName;
	}

	public void setInstrumentName(String instrumentName) {
		this.instrumentName = instrumentName;
	}

	public String getInstrumentTicker() {
		return instrumentTicker;
	}

	public void setInstrumentTicker(String instrumentTicker) {
		this.instrumentTicker = instrumentTicker;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String doOrder() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		ClientDTO clientDTO = (ClientDTO) session.getAttribute("client");
		int clientID = clientDTO.getClient();
		Service service = new Service();
		//TODO: implement api for price
		if (service.enoughBalance(clientDTO.getEmail(), quantity * price)) {
			service.insertOrder("Vodafone", "VOD", clientID, price, quantity, type);
			service.deductBalance(clientDTO.getEmail(), quantity * price);
			ClientDTO newClientDTO = service.getClient(clientDTO.getEmail());
			session.setAttribute("client", newClientDTO);
			return "dashboard?faces-redirect=true";
		}
		return null;
	}
}