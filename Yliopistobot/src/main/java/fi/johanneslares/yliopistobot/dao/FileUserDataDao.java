/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.johanneslares.yliopistobot.dao;

import com.google.gson.Gson;
import fi.johanneslares.yliopistobot.Chat;
import fi.johanneslares.yliopistobot.User;
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
public class FileUserDataDao implements UserDataDao {

    private String file = "user.json";
    
    @Override
    public void createUser(User user) {
        Gson gson = new Gson();
        String json = gson.toJson(user);
        try {
            FileWriter writer = new FileWriter(new File(file), true);
            writer.write(json + "\n");
            System.out.println(json + "\n");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUser(long chatId) {
        Gson gson = new Gson();
        User user = new User(chatId);
        try {
            FileReader reader = new FileReader(new File(file));
            BufferedReader br = new BufferedReader(reader);
            String line;
            boolean found = false;
            while ((line = br.readLine()) != null) {
                user = gson.fromJson(line, User.class);
                if (user.getChatId() == chatId) {
                    return user;
                }
            }
            if (!found) {
                user.setChatId(0);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return user;
    }
    
    @Override
    public void updateUser(User user) {
        Gson gson = new Gson();
        try {
            FileReader reader = new FileReader(new File(file));
            BufferedReader br = new BufferedReader(reader);
            String line;
            User fileUser;
            String content = "";
            while ((line = br.readLine()) != null) {
                fileUser = gson.fromJson(line, User.class);
                System.out.println(line);
                if (fileUser.getChatId() == user.getChatId()) {
                    content += gson.toJson(user) + "\n";
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
    
    @Override
    public List<User> getUsers() {
        Gson gson = new Gson();
        List<User> users = new ArrayList<>();
        try {
            FileReader reader = new FileReader(new File(file));
            BufferedReader br = new BufferedReader(reader);
            String line;
            boolean found = false;
            while ((line = br.readLine()) != null) {
                User user = gson.fromJson(line, User.class);
                users.add(user);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return users;
    }
    
}
