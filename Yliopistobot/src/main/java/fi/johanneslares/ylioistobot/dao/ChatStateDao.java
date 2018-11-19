/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.johanneslares.ylioistobot.dao;

import fi.johanneslares.yliopistobot.Chat;

/**
 *
 * @author jlares
 */
public interface ChatStateDao {
    long createChat(Chat chat);
    
    Chat getChatState(long chatId);
    
    void updateChat(Chat chat);
}
