/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.johanneslares.yliopistobot.dao;

import com.google.gson.*;
import fi.johanneslares.yliopistobot.Chat;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jlares
 */
public class FileChatStateDao implements ChatStateDao {
    
    private String file = "./chatstate.json";
    @Override
    public long createChat(Chat chat) {
        Gson gson = new Gson();
        String json = gson.toJson(chat);
        try {
            FileWriter writer = new FileWriter(new File(file), true);
            writer.write(json + "\n");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chat.getChatId();
    }

    @Override
    public Chat getChatState(long chatId) {
        Gson gson = new Gson();
        Chat chat = new Chat();
        try {
            FileReader reader = new FileReader(new File(file));
            BufferedReader br = new BufferedReader(reader);
            String line;
            boolean found = false;
            while ((line = br.readLine()) != null) {
                chat = gson.fromJson(line, Chat.class);
                if (chat.getChatId() == chatId) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                chat.setChatId(0);
                chat.setStart(-1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return chat;
    }
    
    @Override
    public void updateChat(Chat chat) {
        Gson gson = new Gson();
        try {
            FileReader reader = new FileReader(new File(file));
            BufferedReader br = new BufferedReader(reader);
            String line;
            Chat fileChat;
            String content = "";
            while ((line = br.readLine()) != null) {
                fileChat = gson.fromJson(line, Chat.class);
                if (fileChat.getChatId() == chat.getChatId()) {
                    content += gson.toJson(chat) + "\n";
                    break;
                } else {
                    content += line + "\n";
                }
            }
            FileWriter writer = new FileWriter(new File(file), false);
            writer.write(content);
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void write(String content) {
        try {
            FileWriter writer = new FileWriter(new File(file), false);
            writer.write(content);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(FileChatStateDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
