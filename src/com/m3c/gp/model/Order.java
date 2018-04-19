package com.m3c.gp.model;

public interface Order {
	public Instrument getInstrument();
	public boolean setInstrument();
	public Integer getClientId();
	public boolean setClientId();
	public Integer getPrice();
	public boolean setPrice();

	public orderType getType();
	public boolean setType(); 
	public Integer getQuantity();
	public boolean setQuantity();
}
