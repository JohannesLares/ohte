package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void saldoAlussaOikein(){
        assertEquals(10,kortti.saldo());
    }
    
    @Test
    public void lataaminenKasvattaaSaldoaOikein(){
        kortti.lataaRahaa(1000);
        assertEquals(1010, kortti.saldo());
    }
    
    @Test
    public void otaRahaaOttaaRahaaJosRahaa(){
        kortti.otaRahaa(5);
        assertEquals(5, kortti.saldo());
    }
    
    @Test
    public void otaRahaaReturnTrueJosOnRahaa(){
        assertEquals(true, kortti.otaRahaa(5));
    }
    
    @Test 
    public void otaRahaaReturnFalseJosEiRahaa(){
        assertEquals(false, kortti.otaRahaa(11));
    }
    
    @Test
    public void saldoEiMuutuJosEiRahaa(){
        kortti.otaRahaa(11);
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void toStringAntaaOikein(){
        assertEquals("saldo: 0.10", kortti.toString());
    }
}
