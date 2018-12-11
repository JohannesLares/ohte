/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.johanneslares.yliopistobot;

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
        String msg = ItineraryService.getItinerary("Pietari Kalmin katu 5", "Pasteurinkatu 1c65", "19:00", "JUNITTEST");
        assertEquals(msg.contains("JUNITTEST"), false);
    }
    
    @Test
    public void getMethodOfTransportationWorks() {
        String kav = ItineraryService.getMethodOfTransportation("WALK");
        assertEquals(kav, "kävellen");
    }
}
