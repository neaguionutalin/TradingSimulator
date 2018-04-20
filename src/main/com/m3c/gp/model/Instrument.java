package main.com.m3c.gp.model;

/**
 * @author: Ali Saleem
 * @since: 19/04/18
 * @version: 1.0
 * Instrument class represents an instrument for an Order.
 */
public class Instrument implements InstrumentInterface {

	String ticker;
	String name;

	public Instrument(String ticker, String name) {
		this.ticker = ticker;
		this.name = name;
	}

	@Override
	public String getTicker() {
		return this.ticker;
	}

	@Override
	public void setTicker(String ticker) {
		this.ticker = ticker;
		
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
}
