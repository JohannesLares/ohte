package fi.johanneslares.yliopistobot;

import fi.johanneslares.ylioistobot.dao.ChatStateDao;
import fi.johanneslares.ylioistobot.dao.FileChatStateDao;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * This class is mainly for application logics
 * 
 * @author jlares
 */
public class Yliopistobotti extends TelegramLongPollingBot {
    
    private ChatStateDao fcsd = new FileChatStateDao();
    
    @Override
    public String getBotToken() {
        return "Dataa";
    }
    //TODO JSON Document to save chat state, ex. if start was last command sent.

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update.getMessage().getChat().getFirstName() + ": "+update.getMessage().getText());
        long chatId = update.getMessage().getChatId();
        if(update.getMessage().getText().equals("/start")){
            Chat chat = new Chat();
            chat.setChatId(chatId);
            chat.setStart(1);
            this.fcsd.createChat(chat);
            sendMessage(chatId, "Hei, olet aloittamassa menossa luennolle aamulla botin käytön. \nSeuraavaksi tarvitsen kotipaikkasi.");
        }else{
            Chat chat = fcsd.getChatState(chatId);
            if(chat.getChatId()>0 && chat.getStartStatus() != 2){
                sendMessage(chat.getChatId(), "Kiitos");
                chat.setStart(2);
                this.fcsd.updateChat(chat);
                sendMessage(chat.getChatId(), "Voit lisätä uusia luentoja komenolla /lisaa");
            }else{
                sendMessage(chat.getChatId(), "In progress");
            }
        }
        
    }

    @Override
    public String getBotUsername() {
        return "Yliopistobot";
    }
    
    
    private void sendMessage(long chatId, String msg){
        SendMessage message = new SendMessage().setChatId(chatId).setText(msg);
        try {
            execute(message);
        } catch (TelegramApiException ex) {
            ex.printStackTrace();
        }
    }
}