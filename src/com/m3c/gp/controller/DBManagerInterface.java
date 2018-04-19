package com.m3c.gp.controller;

/**
 * Author: Metin Dagcilar
 * Date: 19/04/18
 * Database Manager interface
 */

public interface DBManagerInterface{
	void insertOrder(Order order);
	Order getOrder(Integer clientId);
}