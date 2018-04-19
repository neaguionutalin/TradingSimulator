package main.com.m3c.gp.model;

public interface OrderInterface {
	public Instrument getInstrument();
	public Integer getClientId();
	public Integer getPrice();
	public boolean setPrice(Integer price);

	public OrderType getType();
	public Integer getQuantity();
}
