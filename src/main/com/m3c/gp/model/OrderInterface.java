package main.com.m3c.gp.model;

public interface OrderInterface {
	public Integer getOrderId();
	public Instrument getInstrument();
	public Integer getClientId();
	public Integer getPrice();
	public void setPrice(Integer price);

	public OrderType getType();
	public Integer getQuantity();
}
