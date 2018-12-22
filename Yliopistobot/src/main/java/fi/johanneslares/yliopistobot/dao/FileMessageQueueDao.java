/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.johanneslares.yliopistobot.dao;

import com.google.gson.Gson;
import fi.johanneslares.yliopistobot.Message;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jlares
 */
public class FileMessageQueueDao implements MessageQueueDao {

    private String file = "MessageQueue.json";
    
    @Override
    public void addMessage(Message message) {
        Gson gson = new Gson();
        String json = gson.toJson(message);
        try {
            FileWriter writer = new FileWriter(new File(file), true);
            writer.write(json + "\n");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Message> getMessages() {
        Gson gson = new Gson();
        List<Message> messages = new ArrayList<>();
        try {
            FileReader reader = new FileReader(new File(file));
            BufferedReader br = new BufferedReader(reader);
            String line;
            boolean found = false;
            while ((line = br.readLine()) != null) {
                Message message = gson.fromJson(line, Message.class);
                messages.add(message);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return messages;
    }

    @Override
    public List<Message> getMessagesWithSendTime(long time) {
        List<Message> messagesWithTime = new ArrayList<>();
        List<Message> messages = getMessages();
        for (Message m : messages) {
            if (m.getSendTime() == time) {
                messagesWithTime.add(m);
            }
        }
        return messagesWithTime;
    }
    
    @Override
    public void deleteMessagesWithSendTime(long time) {
        Gson gson = new Gson();
        try {
            FileReader reader = new FileReader(new File(file));
            BufferedReader br = new BufferedReader(reader);
            String line;
            Message fileMessage;
            String content = "";
            while ((line = br.readLine()) != null) {
                fileMessage = gson.fromJson(line, Message.class);
                if (fileMessage.getSendTime() == time) {
                    continue;
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
}
