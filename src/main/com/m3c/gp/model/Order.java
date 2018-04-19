package main.com.m3c.gp.model;

import javax.sound.midi.Instrument;

public class Order implements OrderInterface {
	private Integer orderId;
	private Instrument instrument;
	private Integer clientId;
	private Integer price;
	private Integer quantity;
	private OrderType type;
	
	public Order(Integer orderId, Instrument instrument, Integer clientId, 
				Integer price, Integer quantity, OrderType type) {
		this.orderId = orderId;
		this.instrument = instrument;
		this.clientId = clientId;
		this.price = price;
		this.quantity = quantity;
		this.type = type;
	}
	
	public Integer getOrderId() {
		return orderId;
	}
	@Override
	public Instrument getInstrument() {
		return instrument;
	}

	@Override
	public Integer getClientId() {
		return clientId;
	}

	@Override
	public Integer getPrice() {
		return price;
	}

	@Override
	public boolean setPrice(Integer price) {
		this.price = price;
		return true;
	}

	@Override
	public OrderType getType() {
		return type;
	}

	@Override
	public Integer getQuantity() {
		// TODO Auto-generated method stub
		return quantity;
	}
}
