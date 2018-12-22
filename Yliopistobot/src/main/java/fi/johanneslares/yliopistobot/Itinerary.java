/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.johanneslares.yliopistobot;


/**
 * Itinerary object
 *
 * @author jlares
 */
public class Itinerary {
    private String itinerary;
    private long gotime;
    
    /**
     * 
     * @param itinerary message, that contains the itinerary
     * @param gotime When you should leave from home.
     */
    public Itinerary(String itinerary, long gotime) {
        this.gotime = gotime/1000L;
        this.itinerary = itinerary;
    }
    
    public String getItinerary() {
        return this.itinerary;
    }
    
    public long getGotime() {
        return this.gotime;
    }
}
