package test.com.m3c.gp.model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import main.com.m3c.gp.model.Instrument;
import main.com.m3c.gp.model.InstrumentTicker;

/**
 * @authour: Ali Saleem
 * @since: 19/04/18
 * @version 1.0
 * Instrument test
 */

public class InstrumentTest {
	private Instrument instrument;
	
	@Before
	public void before() {
		instrument = new Instrument(InstrumentTicker.VOD);
	}
	
	@Test
	public void getTickerTest(){
		InstrumentTicker ticker = InstrumentTicker.VOD;
		assertEquals(ticker, instrument.getTicker());
	}
	
	@Test
	public void setTickerTest(){
		InstrumentTicker ticker = InstrumentTicker.BP;
		assertEquals(InstrumentTicker.VOD, instrument.getTicker());
		instrument.setTicker(ticker);
		assertEquals(ticker, instrument.getTicker());
	}
	
	@Test
	public void setNameTest(){
		
	}
	
	@Test
	public void getNameTest(){
		
	}

}