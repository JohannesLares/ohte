/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.johanneslares.yliopistobot.dao;

import fi.johanneslares.yliopistobot.User;

/**
 *
 * @author jlares
 */
public interface UserDataDao {
    void createUser(User user);
    void updateUser(User user);
    User getUser(long chatId);
    
}
