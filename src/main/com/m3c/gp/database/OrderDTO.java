package main.com.m3c.gp.database;

import main.com.m3c.gp.model.Instrument;
import main.com.m3c.gp.model.OrderType;

public class OrderDTO {

	private Instrument instrument;
	private int clientId;
	private double price;
	private int quantity;
	private OrderType type;

	public OrderDTO(int orderId, Instrument instrument, int clientId, double price, int quantity, OrderType orderType) {
		this.instrument = instrument;
		this.clientId = clientId;
		this.price = price;
		this.quantity = quantity;
		this.type = orderType;
	}

	public Instrument getInstrument() {
		return instrument;
	}

	public int getClientId() {
		return clientId;
	}

	public double getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public OrderType getType() {
		return type;
	}
}
