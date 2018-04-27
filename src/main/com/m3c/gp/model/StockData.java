package main.com.m3c.gp.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

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
}
