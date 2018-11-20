/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.johanneslares.yliopistobot;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;


public class YliopistobottiTest {
    
    Yliopistobotti botti;
    
    @Before
    public void setUp() {
        this.botti = new Yliopistobotti();
    }
    
    @Test
    public void getBotUsernameReturnsCorrectValue(){
        assertEquals("Yliopistobot", botti.getBotUsername());
    }
}
