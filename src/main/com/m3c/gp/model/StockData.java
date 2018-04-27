package main.com.m3c.gp.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.primefaces.util.SharedStringBuilder;

/**
 * @authour: Ali Saleem/Alessandro Noiato
 * @since: 19/04/18
 * @version 1.0
 * Stock Data class. This class extracts the data from the NYSE with the AlphaVantage API.
 */
public class StockData {
	private String urlString;
	private final String alphaVantageKey = "WLWSM9CTLOJXEQXU";

	public JSONObject getData(StockUpdateFrequency freq, String ticker) throws IOException, ParseException {
		String frequency = freq.toString();
		urlString = makeURL(frequency, ticker);
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
	
	public String getPrice(String ticker) throws IOException, ParseException{
		urlString = makeURL("TIME_SERIES_INTRADAY", ticker);
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
        JSONObject objectData = new JSONObject();
        objectData = (JSONObject) parser.parse(jsonResult);
        String[] data;
        String key = "";
        for(Object object : objectData.keySet()){
        	key = (String)object;
        	break;
        }
        
        JSONObject meta = (JSONObject) objectData.get("Meta Data");
        System.out.println(meta);
        Object latest;
        latest = meta.get("3. Last Refreshed");
        System.out.println(latest);
        JSONObject latestData = (JSONObject) objectData.get(key);
        latestData = (JSONObject) latestData.get(latest);
        Object temp = latestData.get("4. close");
        String price = temp.toString();
		return price;
	}
}
