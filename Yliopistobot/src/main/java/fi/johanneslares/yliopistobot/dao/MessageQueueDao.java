/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.johanneslares.yliopistobot.dao;

import fi.johanneslares.yliopistobot.Message;
import java.util.List;

/**
 *
 * @author jlares
 */
public interface MessageQueueDao {
    void addMessage(Message message);
    List<Message> getMessages();
    List<Message> getMessagesWithSendTime(long time);
    void deleteMessagesWithSendTime(long time);
}
