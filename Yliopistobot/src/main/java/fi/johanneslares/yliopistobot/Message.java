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
public class Message {
    private long chatId;
    private String message;
    private long sendTime;
    
    public Message(long chatId, String msg, long sendTime) {
        this.chatId = chatId;
        this.message = msg;
        this.sendTime = sendTime;
    }
    
    public long getChatId() {
        return this.chatId;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public long getSendTime() {
        return this.sendTime;
    }
}
