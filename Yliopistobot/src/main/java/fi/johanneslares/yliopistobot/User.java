/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.johanneslares.yliopistobot;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jlares
 */
public class User {
    
    private long chatId;
    private String homeLocation;
    private List<Lesson> lessons;
    
    public User(long chatId) {
        this.chatId = chatId;
        this.homeLocation = "";
        this.lessons = new ArrayList<>();
    }
    
    public void setChatId(long id) {
        this.chatId = id;
    }
    
    public void setHomeLocation(String location) {
        this.homeLocation = location;
    }
    
    public void addLesson(Lesson lesson) {
        this.lessons.add(lesson);
    }
    
    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
    
    public long getChatId() {
        return this.chatId;
    }
    
    public String getHomeLocation() {
        return this.homeLocation;
    }
    
    public List<Lesson> getLessons() {
        return this.lessons;
    }
    
    @Override
    public String toString() {
        return this.chatId + " : " + this.homeLocation;
    }
    
}
