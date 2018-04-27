package main.com.m3c.gp.model;

import java.util.HashMap;
import java.util.Map;

public class MarketData {

	private Map<String,String> instruments = new HashMap<String, String>();
	{
		instruments.put("FB","Facebook");
		instruments.put("GOOGL","Alphabet");
		instruments.put("TSLA","Tesla");
		instruments.put("AMZN","Amazon");
		instruments.put("MS","Morgan Stanley");
		instruments.put("VOD","Vodafone");
		instruments.put("MS","Morgan Stanley");
		instruments.put("BKG","Berkeley Group");
		instruments.put("GSK","GlaxoSmithKline");
		instruments.put("BA","BAE Systems");
		instruments.put("BATS","British American Tobacco");
	}
	
	public MarketData(){

	}
}
