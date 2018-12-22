/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.johanneslares.yliopistobot.threads;

import fi.johanneslares.yliopistobot.Message;
import fi.johanneslares.yliopistobot.Yliopistobotti;
import fi.johanneslares.yliopistobot.dao.FileMessageQueueDao;
import fi.johanneslares.yliopistobot.dao.MessageQueueDao;
import java.util.List;

/**
 *
 * @author jlares
 */
public class AutoMessager extends Thread {
    
    public AutoMessager () {
        this.start();
    }

    @Override
    public void run() {
        Yliopistobotti bot = new Yliopistobotti();
        MessageQueueDao mqd = new FileMessageQueueDao();
        while (true) {
            try {
                long time = System.currentTimeMillis()/1000L;
                List<Message> messages = mqd.getMessagesWithSendTime(time);
                mqd.deleteMessagesWithSendTime(time);
                for (Message message : messages) {
                    bot.sendMessage(message.getChatId(), message.getMessage());
                }
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    
}
