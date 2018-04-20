package test.com.m3c.gp.model;
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import main.com.m3c.gp.model.Instrument;
import main.com.m3c.gp.model.StockData;
import main.com.m3c.gp.model.StockUpdateFrequency;

/**
 * @authour: Ali Saleem/Alessandro Noiato
 * @since: 19/04/18
 * @version 1.0
 * StockDataTest tests the StockData class.
 */

public class StockDataTest {
	StockData sd;
	StockUpdateFrequency frequency;
	String ticker;
	
	@Before
	public void setup(){
		sd = new StockData();
		ticker = "VOD";
	}
	
	@Test
	public void testIntraDayData() throws IOException{
		frequency = StockUpdateFrequency.TIME_SERIES_INTRADAY;
		String result;
		result = sd.getData(frequency, ticker);
		String[] resultArray = result.split("\n");
		assertTrue(resultArray[2].contains(""));
	}
}
