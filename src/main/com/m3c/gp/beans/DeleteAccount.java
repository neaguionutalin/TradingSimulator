package main.com.m3c.gp.beans;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import main.com.m3c.gp.database.ClientDTO;
import main.com.m3c.gp.database.Service;

public class DeleteAccount {
	
	public String doDelete() {
		Service service = new Service();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		ClientDTO clientDTO = (ClientDTO) session.getAttribute("client");
		String email = clientDTO.getEmail();
		service.deleteClient(email);
		return "index?faces-redirect=true";
	}

}
