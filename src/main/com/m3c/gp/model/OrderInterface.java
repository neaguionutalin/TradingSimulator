package main.com.m3c.gp.model;

public interface OrderInterface {
	public InstrumentInterface getInstrument();
	public boolean setInstrument();
	public Integer getClientId();
	public boolean setClientId();
	public Integer getPrice();
	public boolean setPrice();

	public OrderType getType();
	public boolean setType(); 
	public Integer getQuantity();
	public boolean setQuantity();
}
