package main.com.m3c.gp.beans;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import main.com.m3c.gp.database.ClientDTO;

public class Buy {
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		ClientDTO clientDTO = (ClientDTO) session.getAttribute("client");
		int clientID = clientDTO.getClient();
	}
}
