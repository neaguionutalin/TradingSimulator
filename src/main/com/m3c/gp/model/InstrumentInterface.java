package main.com.m3c.gp.model;

/**
 * Author: Ali Saleem
 * Date: 19/04/18
 * Database Manager interface
 */

public interface InstrumentInterface {      
    String getTicker();
    void setTicker(String ticker);
    String getName();
    void setName(String name);
}
