package test.com.m3c.gp.model;

import static org.junit.Assert.*;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import main.com.m3c.gp.model.Instrument;
import main.com.m3c.gp.model.StockData;
import main.com.m3c.gp.model.StockUpdateFrequency;

/**
 * @authour: Ali Saleem/Alessandro Noiato
 * @since: 19/04/18
 * @version 1.0 StockDataTest tests the StockData class.
 */

public class StockDataTest {
	StockData sd;
	String frequency;
	String ticker;

	@Before
	public void setup() {
		sd = new StockData();
		ticker = "VOD";
	}

	@Test
	public void testInstrumentReturned() {
		frequency = StockUpdateFrequency.TIME_SERIES_INTRADAY.toString();
		String result = sd.makeURL(frequency, ticker);
		assertTrue(result.contains("VOD"));
	}

	@Test
	public void testIntraDayData() throws IOException, ParseException {
		frequency = StockUpdateFrequency.TIME_SERIES_INTRADAY.toString();
		String result;
		result = sd.makeURL(frequency, ticker);
		assertTrue(result.contains("INTRADAY"));
	}

	@Test
	public void testDailyData() {
		frequency = StockUpdateFrequency.TIME_SERIES_DAILY.toString();
		String result;
		result = sd.makeURL(frequency, ticker);
		assertTrue(result.contains("DAILY"));
	}
	
	@Test
	public void testWeeklyData(){
		frequency = StockUpdateFrequency.TIME_SERIES_WEEKLY.toString();
		String result;
		result = sd.makeURL(frequency, ticker);
		assertTrue(result.contains("WEEKLY"));
	}
	
	@Test
	public void testMonthlyData(){
		frequency = StockUpdateFrequency.TIME_SERIES_MONTHLY.toString();
		String result;
		result = sd.makeURL(frequency, ticker);
		assertTrue(result.contains("MONTHLY"));
	}
}
