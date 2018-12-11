/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.johanneslares.yliopistobot;

/**
 * Stores chat as object.
 * @author jlares
 */
public class Chat {
    /*
     * start; 
     * 1:/start message sent
     * 2:Thank you message sent
     * 3-10: reserved for adding lessons
     */
    private long chatId;
    private int start;
    private Lesson lesson;
    
    /**
     * Create new chat object
     */
    public Chat() {
        start = 0;
    }
    
    /**
     * 
     * @param chatId ChatId of telegram chat 
     */
    public void setChatId(long chatId) {
        this.chatId = chatId;
    }
    
    /**
     * 
     * @param st describes chat state, ex. 1 = start message, 2= thank you message sent, 3-10 reserved for adding lessons. 
     */
    public void setStart(int st) {
        this.start = st;
    }
    
    /**
     * 
     * @return chatId 
     */
    public long getChatId() {
        return this.chatId;
    }
    
    /**
     * 
     * @return chat status
     */
    public int getStartStatus() {
        return this.start;
    }
    
    /**
     * 
     * @param lesson bind lesson to chat, before it's stored to user
     */
    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }
    
    /**
     * 
     * @return lesson that have been bound to chat for cache like storage. 
     */
    public Lesson getLesson() {
        return this.lesson;
    }
}
