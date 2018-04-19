package main.com.m3c.gp.model;

/**
 * @author: Ali Saleem
 * @since: 19/04/18
 * @version: 1.0
 * Intstrument class represents an instrument for an Order.
 */
public class Instrument implements InstrumentInterface {

	InstrumentTicker ticker;
	String name;

	public Instrument(InstrumentTicker ticker) {
		this.ticker = ticker;
	}

	@Override
	public InstrumentTicker getTicker() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTicker(InstrumentTicker ticker) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}

}
