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
     */
    private long chatId;
    private int start;
    public Chat(){
        start = 0;
    }
    
    public void setChatId(long chatId){
        this.chatId = chatId;
    }
    
    public void setStart(int st){
        this.start = st;
    }
    
    public long getChatId(){
        return this.chatId;
    }
    
    public int getStartStatus(){
        return this.start;
    }
}
