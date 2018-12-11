/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.johanneslares.yliopistobot.dao;

import fi.johanneslares.yliopistobot.User;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jlares
 */
public class FileUserDataDaoTest {
    
    User user;
    UserDataDao udd;
    
    @Before
    public void setUp() {
        user = new User(123);
        udd = new FileUserDataDao();
    }
    
    
    
    @After
    public void tearDown() {
        FileWriter writer;
        try {
            writer = new FileWriter(new File("user.json"), false);
            writer.write("");
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(FileChatStateDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void createUserWorks() {
        user.setHomeLocation("Viikki");
        udd.createUser(user);
        User read = udd.getUser(123);
        assertEquals(read.getHomeLocation(), "Viikki");
        tearDown();
    }
    
    @Test
    public void updateUserWorks() {
        user.setHomeLocation("Viikki");
        udd.createUser(user);
        user.setHomeLocation("Helsinki");
        udd.updateUser(user);
        User read = udd.getUser(123);
        assertEquals(read.getHomeLocation(), "Helsinki");
        tearDown();
    }
    
    @Test
    public void getUsersWorks() {
        udd.createUser(user);
        User user2 = new User(12345);
        User user3 = new User(321);
        udd.createUser(user2);
        udd.createUser(user3);
        assertEquals(udd.getUsers().size(), 3);
        assertEquals(udd.getUsers().get(1).getChatId(), 12345);
        tearDown();
    }
    
    @Test
    public void getUserReturnsChatId0() {
        User us = udd.getUser(1234567);
        assertEquals(us.getChatId(), 0);
    }
}
