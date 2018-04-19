package com.m3c.gp.model;

/**
 * Author: Ali Saleem
 * Date: 19/04/18
 * Database Manager interface
 */

public interface Instrument {
      
    InstrumentName getName();
    void setName(InstrumentName name);
    String getType();
    void setType(String type);
}
