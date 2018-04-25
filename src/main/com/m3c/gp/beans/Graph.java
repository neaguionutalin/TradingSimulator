package main.com.m3c.gp.beans;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.faces.bean.RequestScoped;
import javax.inject.Named;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import main.com.m3c.gp.model.StockData;
import main.com.m3c.gp.model.StockUpdateFrequency;

@Named
@RequestScoped
public class Graph {
	static final String alphaVantageKey = "WLWSM9CTLOJXEQXU";
	private String ticker = "";
	private String frequency;
	
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
		
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	
	public String getTicker(){
		return ticker;
	}
	
	public String getFrequency() {
		return frequency;
	}
	
	public JSONObject getSampleData() throws IOException, ParseException {
		StockData  stockData = new StockData();
		System.out.println("Frequency: "+frequency);
		if(frequency == StockUpdateFrequency.TIME_SERIES_INTRADAY.toString()) {
			return stockData.getData(StockUpdateFrequency.TIME_SERIES_INTRADAY, ticker);
		}
		else if(frequency == StockUpdateFrequency.TIME_SERIES_DAILY.toString()){
			return stockData.getData(StockUpdateFrequency.TIME_SERIES_DAILY, ticker);
		}
		else if(frequency == StockUpdateFrequency.TIME_SERIES_WEEKLY.toString()){
			return stockData.getData(StockUpdateFrequency.TIME_SERIES_WEEKLY, ticker);
		}
		else {
			return stockData.getData(StockUpdateFrequency.TIME_SERIES_MONTHLY, ticker);
		}
	}
}
