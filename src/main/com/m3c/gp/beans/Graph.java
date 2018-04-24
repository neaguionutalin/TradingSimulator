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

@Named
@RequestScoped
public class Graph {
	static final String alphaVantageKey = "WLWSM9CTLOJXEQXU";
	private String ticker = "";
//	private String frequency;
	
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
//	public void setFrequency(String frequency) {
//		this.frequency = frequency;
//	}
	public String getTicker(){
		return ticker;
	}
//	public String getFrequency() {
//		return frequency;
//	}
	
	public JSONObject getSampleData() throws IOException, ParseException {
		String frequency = "TIME_SERIES_DAILY";
		System.out.println("Ticker - " + ticker);
		String urlString = makeURL(frequency, ticker);
        URL url = new URL(urlString);

        url.openConnection();
        InputStream is = url.openStream();

        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String sr;
        String jsonResult = "";

        while ((sr = br.readLine()) != null) {
            jsonResult += sr + "\n";
        }
        br.close();
        JSONParser parser = new JSONParser();
        JSONObject object = new JSONObject();
        object = (JSONObject) parser.parse(jsonResult);
        return object;
	}
	
	public String makeURL(String frequency, String ticker){
		return "https://www.alphavantage.co/query?function="+frequency+"&symbol=" + ticker.toUpperCase() + "&interval=1min&apikey=" + alphaVantageKey
                + "&datatype=json";
		
	}
}
