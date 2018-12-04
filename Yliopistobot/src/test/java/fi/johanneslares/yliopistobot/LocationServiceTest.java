/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.johanneslares.yliopistobot;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jlares
 */
public class LocationServiceTest {
    
    @Test
    public void locationServiceWorks() {
        String coordinates = LocationService.getCoordinates("Pietari Kalminkatu 5");
        assertEquals(coordinates, "60.468913 22.289951");
    }
    
}
