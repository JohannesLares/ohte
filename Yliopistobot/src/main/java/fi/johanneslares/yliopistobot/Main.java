/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.johanneslares.yliopistobot;

import fi.johanneslares.yliopistobot.threads.AutoSender;
import fi.johanneslares.yliopistobot.threads.AutoMessager;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;

public class Main {
    /**
     * This class is only to start the bot
     * If you want to stop the bot, you must terminate this program. Else the threads will be left running.
     * @param args 
     */
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi api = new TelegramBotsApi();
        try {
            api.registerBot(new Yliopistobotti());
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        AutoMessager m = new AutoMessager();
        AutoSender s = new AutoSender("Eka");
    }
}
