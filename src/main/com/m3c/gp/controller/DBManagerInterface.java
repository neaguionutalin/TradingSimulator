package main.com.m3c.gp.controller;

import main.com.m3c.gp.model.OrderInterface;

/**
 * Author: Metin Dagcilar
 * Date: 19/04/18
 * Database Manager interface
 */

public interface DBManagerInterface{
	void insertOrder(OrderInterface order);
	OrderInterface getOrder(Integer clientId);
}