/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.johanneslares.yliopistobot;

/**
 *
 * @author jlares
 */
public class Chat {
    /**
     * start; 
     * 1:/start message sent
     * 2:Thank you message sent
     * 3-10: reserved for adding lessons
     */
    /**
     * Lesson is only for cache like storage
     */
    private long chatId;
    private int start;
    private Lesson lesson;
    public Chat() {
        start = 0;
    }
    
    public void setChatId(long chatId) {
        this.chatId = chatId;
    }
    
    public void setStart(int st) {
        this.start = st;
    }
    
    public long getChatId() {
        return this.chatId;
    }
    
    public int getStartStatus() {
        return this.start;
    }
    
    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }
    
    public Lesson getLesson() {
        return this.lesson;
    }
}
