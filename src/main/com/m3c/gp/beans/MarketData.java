package main.com.m3c.gp.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import main.com.m3c.gp.database.Service;
import main.com.m3c.gp.model.Instrument;

import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
public class MarketData {

	private List<Instrument> instruments;
	
	public List<Instrument> getMarketData(){
		if(instruments == null){
			populateInstro();
		}
		return instruments;
	}
	
	private void populateInstro(){
		Service service = new Service();		
		instruments= service.getInstruments();
		
		
	}
	
}
