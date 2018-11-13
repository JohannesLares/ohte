/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jlares
 */
public class KassapaateTest {
    
    Kassapaate kassa;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(10000);
    }

    @Test
    public void alussaOikeaMaaraRahaa(){
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void alusaaOikeaMaaraEdullisiaLounaitaMyyty(){
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void alussaOikeaMaaraMaukkaitaLounaitaMyyty(){
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void syoEdullisestiKateisellaPalauttaaOikeanMaaran(){
        assertEquals(110, kassa.syoEdullisesti(350));
    }
    
    @Test
    public void syoMaukkaastiKateisellaPalauttaOikeanMaaran(){
        assertEquals(100, kassa.syoMaukkaasti(500));
    }
    
    @Test
    public void josLiianVahanSyoEdullisestiToimiiOikein(){
        assertEquals(200, kassa.syoEdullisesti(200));
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void josLiianVahanSyoMaukkaastiToimiiOikein(){
        assertEquals(300, kassa.syoMaukkaasti(300));
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void maukkaastiSyominenKasvattaaMaaraa(){
        kassa.syoMaukkaasti(400);
        kassa.syoMaukkaasti(500);
        assertEquals(2, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullisestiSyominenKasvattaaMaaraa(){
        kassa.syoEdullisesti(259);
        kassa.syoEdullisesti(300);
        kassa.syoEdullisesti(10);
        assertEquals(2, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaastiKasvattaaKassaaOikein(){
        kassa.syoMaukkaasti(1000);
        kassa.syoMaukkaasti(500);
        kassa.syoMaukkaasti(100);
        assertEquals(100800, kassa.kassassaRahaa());
    }
    
    @Test
    public void edullisestiSyominenKasvattaaKassaaOikein(){
        kassa.syoEdullisesti(1000);
        kassa.syoEdullisesti(10);
        assertEquals(100240, kassa.kassassaRahaa());
    }
    
    @Test
    public void myytyjenLounaidenMaaraEiMuutuJosLiianVahanRahaa(){
        kassa.syoEdullisesti(10);
        assertEquals(0,kassa.edullisiaLounaitaMyyty());
        kassa.syoMaukkaasti(100);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void ladatessaKortilleKortinSaldoKasvaa(){
        kassa.lataaRahaaKortille(kortti, 100);
        assertEquals(10100, kortti.saldo());
    }
    
    @Test
    public void ladatessaKortilleKassaSaldoKasvaaOikein(){
        kassa.lataaRahaaKortille(kortti, 100);
        assertEquals(100100, kassa.kassassaRahaa());
    }
    
    @Test
    public void kortilleVoiLadataVainPositiivisenMaaran(){
        kassa.lataaRahaaKortille(kortti, -1);
        assertEquals(10000, kortti.saldo());
    }
    
    @Test
    public void kortillaRahaaJaEdullinenToimii(){
        assertEquals(true, kassa.syoEdullisesti(kortti));
        assertEquals(9760, kortti.saldo());
    }
    
    @Test
    public void kortillaRahaaJaMaukasToimii(){
        assertEquals(true, kassa.syoMaukkaasti(kortti));
        assertEquals(9600, kortti.saldo());
    }
    
    @Test
    public void kortillaEiRahaaJaMaukasToimii(){
        Maksukortti mk = new Maksukortti(100);
        assertEquals(false, kassa.syoMaukkaasti(mk));
        assertEquals(100, mk.saldo());
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kortillaEiRahaaJaEdullinenToimii(){
        Maksukortti mk = new Maksukortti(100);
        assertEquals(false, kassa.syoEdullisesti(mk));
        assertEquals(100, mk.saldo());
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kassanSaldoEiMuutuKortilla(){
        kassa.syoEdullisesti(kortti);
        kassa.syoMaukkaasti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
}
