package main.com.m3c.gp.beans;

import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import main.com.m3c.gp.database.ClientDTO;
import main.com.m3c.gp.database.Service;

@Named
public class ChangePass {

	private String oldPass;
	private String newPassFirst;
	public String getOldPass() {
		return oldPass;
	}

	public String getNewPassFirst() {
		return newPassFirst;
	}

	public String getNewPassSecond() {
		return newPassSecond;
	}

	private String newPassSecond;
	
	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}
	
	public void setNewPassFirst(String newPassFirst) {
		this.newPassFirst = newPassFirst;
	}
	
	public void setNewPassSecond(String newPassSecond) {
		this.newPassSecond = newPassSecond;
	}
	
	public String doChangePass() {
		if (newPassFirst.equals(newPassSecond)) {
			Service service = new Service();
			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
			ClientDTO clientDTO = (ClientDTO) session.getAttribute("client");
			String email = clientDTO.getEmail();
			service.updatePassword(email, oldPass, newPassFirst);
			return "settings?faces-redirect=true";
		}
		return null;
	}
}
