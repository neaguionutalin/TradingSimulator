package main.com.m3c.gp.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * @authour: Ali Saleem/Alessandro Noiato
 * @since: 19/04/18
 * @version 1.0
 * Stock Data class. This class extracts the data from the NYSE with the AlphaVantage API.
 */
public class StockData {
	static final String alphaVantageKey = "WLWSM9CTLOJXEQXU";
	
	public String getData(StockUpdateFrequency freq, String ticker) throws IOException {
		String frequency = freq.toString();
		String urlString = "https://www.alphavantage.co/query?function="+frequency+"&symbol=" + ticker.toUpperCase() + "&interval=1min&apikey=" + alphaVantageKey
                + "&datatype=json";
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
        return jsonResult;
	}
}
