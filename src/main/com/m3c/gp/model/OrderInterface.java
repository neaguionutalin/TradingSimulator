package main.com.m3c.gp.model;

public interface OrderInterface {
	public int getOrderId();
	public Instrument getInstrument();
	public int getClientId();
	public double getPrice();
	public void setPrice(double price);

	public OrderType getType();
	public int getQuantity();
}
