/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.johanneslares.yliopistobot.dao;

import fi.johanneslares.yliopistobot.Chat;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author jlares
 */
public class FileChatStateDaoTest {
    
    public FileChatStateDaoTest() {
    }
    
    Chat chat;
    FileChatStateDao fstd;
    @Before
    public void setUp() {
        this.chat = new Chat();
        this.fstd = new FileChatStateDao();
    }
    
    
    @Test
    public void createChatWorks(){
        this.chat.setChatId(12345);
        this.chat.setStart(1);
        fstd.createChat(chat);
        assertEquals(1, fstd.getChatState(12345).getStartStatus());
    }
    
    @Test
    public void getChatStateHandlesNoChat(){
        assertEquals(-1, this.fstd.getChatState(1).getStartStatus());
    }
    
    @Test
    public void updateChatStatusWorks(){
        chat.setChatId(123456);
        this.chat.setStart(2);
        fstd.createChat(chat);
        assertEquals(true, this.fstd.updateChat(chat));
        assertEquals(2, fstd.getChatState(123456).getStartStatus());
    }
    
    @AfterClass
    public static void tearDown(){
        FileWriter writer;
        try {
            writer = new FileWriter(new File("chatstate.json"), false);
            writer.write("");
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(FileChatStateDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
