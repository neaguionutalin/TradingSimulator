package main.com.m3c.gp.beans;

import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import main.com.m3c.gp.model.StockData;
import main.com.m3c.gp.model.StockUpdateFrequency;

@Named
@RequestScoped
public class Graph {
	private String ticker;
	private String frequency;
	private JSONObject sampleData;
	
	@PostConstruct
	public void init() {
		ticker = "FB";
		frequency = "TIME_SERIES_INTRADAY";
	}
	
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
		
	public void setFrequency(String frequency) {
		this.frequency = frequency;
		System.out.println("Freq - " + this.frequency);
	}
	
	public String getTicker(){
		return ticker;
	}
	
	public String getFrequency() {
		return frequency;
	}
	
	public JSONObject getData() throws IOException, ParseException {
		StockData  stockData = new StockData();
		System.out.println("Frequency: "+frequency);
		if(frequency.equals("TIME_SERIES_INTRADAY")){
			return stockData.getData(StockUpdateFrequency.TIME_SERIES_INTRADAY, ticker);
		}
		else if(frequency.equals("TIME_SERIES_DAILY")) {
			return stockData.getData(StockUpdateFrequency.TIME_SERIES_DAILY, ticker);
		}
		else if(frequency.equals("TIME_SERIES_WEEKLY")){
			return stockData.getData(StockUpdateFrequency.TIME_SERIES_WEEKLY, ticker);
		}
		else {
			return stockData.getData(StockUpdateFrequency.TIME_SERIES_MONTHLY, ticker);
		}
	}

	public JSONObject getSampleData() {
		return sampleData;
	}

	public void setSampleData(JSONObject sampleData) {
		this.sampleData = sampleData;
	}
}
