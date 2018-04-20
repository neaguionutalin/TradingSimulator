package main.com.m3c.gp.model;

/**
 * @author: Ali Saleem
 * @since: 19/04/18
 * @version: 1.0
 * Instrument class represents an instrument for an Order.
 */
public class Instrument implements InstrumentInterface {

	InstrumentTicker ticker;
	String name;

	public Instrument(InstrumentTicker ticker) {
		this.ticker = ticker;
	}

	@Override
	public InstrumentTicker getTicker() {
		return this.ticker;
	}

	@Override
	public void setTicker(InstrumentTicker ticker) {
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
