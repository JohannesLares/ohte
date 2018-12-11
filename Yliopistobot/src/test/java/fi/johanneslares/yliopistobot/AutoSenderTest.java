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
public class AutoSenderTest {
    
    AutoSender as;
    
    @Before
    public void setUp() {
        as = new AutoSender("test", true);
    }
    
    @Test
    public void autoSenderWorks() {
        assertEquals(as.getName(), "test");
    }

}
