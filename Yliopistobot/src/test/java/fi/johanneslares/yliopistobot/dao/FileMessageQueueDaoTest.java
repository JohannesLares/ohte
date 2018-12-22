/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.johanneslares.yliopistobot.dao;

import fi.johanneslares.yliopistobot.Message;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jlares
 */
public class FileMessageQueueDaoTest {
    
    MessageQueueDao mqd = new FileMessageQueueDao();
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void addMessageWorks() {
        Message msg = new Message(12334L ,"Testiviesti", 123L);
        mqd.addMessage(msg);
        List<Message> msgs = mqd.getMessagesWithSendTime(123L);
        assertEquals(msgs.size(), 1);
    }
    
    @Test
    public void deleteMessageWorks() {
        Message msg = new Message(12334L ,"Testiviesti2", 123L);
        mqd.addMessage(msg);
        mqd.deleteMessagesWithSendTime(123L);
        List<Message> msgs = mqd.getMessagesWithSendTime(123L);
        assertEquals(msgs.size(), 0);
    }
}
