/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.johanneslares.yliopistobot.services;

import fi.johanneslares.yliopistobot.Itinerary;
import fi.johanneslares.yliopistobot.services.ItineraryService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jlares
 */
public class ItineraryServiceTest {

    @Test
    public void getItineraryWorks() {
        Itinerary i = ItineraryService.getItinerary("Pietari Kalmin Katu 5", "Yliopistokatu 1", "10:00", "TEST");
        assertNotNull(i);
    }
    
    @Test
    public void getMethodOfTransportationWorks() {
        String kav = ItineraryService.getMethodOfTransportation("WALK");
        assertEquals(kav, "kävellen");
    }
}
