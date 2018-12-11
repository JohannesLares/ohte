package fi.johanneslares.yliopistobot;

import fi.johanneslares.yliopistobot.dao.ChatStateDao;
import fi.johanneslares.yliopistobot.dao.FileChatStateDao;
import fi.johanneslares.yliopistobot.dao.FileUserDataDao;
import fi.johanneslares.yliopistobot.dao.UserDataDao;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

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
    private UserDataDao udd = new FileUserDataDao();
    
    @Override
    public String getBotToken() {
        String key = "Dataa";
        try {
            FileReader reader = new FileReader(new File("./bot.conf"));
            BufferedReader br = new BufferedReader(reader);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.split("=")[0].equals("Bot_Key")) {
                    key = line.split("=")[1];
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return key;
    }

    @Override
    public void onUpdateReceived(Update update) {
        //This could be multiple methods
        System.out.println(update.getMessage().getChat().getFirstName() + ": " + update.getMessage().getText());
        long chatId = update.getMessage().getChatId();
        Chat chat = fcsd.getChatState(chatId);
        String message = update.getMessage().getText();
        if (message.equals("/start") && chat.getChatId() == 0) {
            this.createNewUser(chatId);
        } else {
            if (chat.getChatId() > 0 && chat.getStartStatus() < 2) {
                this.onHomeLocationReceived(message, chat);
            } else {
                if (message.equals("/lisaa") || (chat.getStartStatus() > 2 && chat.getStartStatus() < 10)) {
                    addNewLesson(chat, update.getMessage().getText());
                }
                if (message.equals("/luennot") && chat.getStartStatus() == 2) {
                    sendUserLessons(chat.getChatId());
                }
            }
        }
        
    }

    @Override
    public String getBotUsername() {
        return "Yliopistobot";
    }
    
    private void createNewUser(long chatId) {
        Chat chat = new Chat();
        chat.setChatId(chatId);
        chat.setStart(1);
        User user = new User(chatId);
        this.udd.createUser(user);
        this.fcsd.createChat(chat);
        sendMessage(chatId, "Hei, olet aloittamassa menossa luennolle aamulla botin käytön. \nSeuraavaksi tarvitsen kotipaikkasi.");
    }
    
    private void onHomeLocationReceived(String message, Chat chat) {
        User user = new User(chat.getChatId());
        System.out.println("UserChatID: " + user.getChatId());
        user.setHomeLocation(message);
        user.setCoordinates(LocationService.getCoordinates(message));
        udd.updateUser(user);
        System.out.println(user.toString());
        System.out.println(LocationService.getCoordinates(message) + " Locationservice");
        sendMessage(chat.getChatId(), "Kiitos");
        chat.setStart(2);
        this.fcsd.updateChat(chat);
        sendMessage(chat.getChatId(), "Voit lisätä uusia luentoja komenolla /lisaa");
    }
    
    
    public void sendMessage(long chatId, String msg) {
        SendMessage message = new SendMessage().setChatId(chatId).setText(msg);
        try {
            execute(message);
        } catch (TelegramApiException ex) {
            ex.printStackTrace();
        }
    }
    
    private void addNewLesson(Chat chat, String msg) {
        Lesson lesson = new Lesson();
        if (chat.getStartStatus() == 2) {
            sendMessage(chat.getChatId(), "Mikä on luennon nimi?");
            chat.setStart(3);
            this.fcsd.updateChat(chat);
        } else if (chat.getStartStatus() == 3) {
            lesson.setName(msg);
            chat.setLesson(lesson);
            sendMessage(chat.getChatId(), "Missä luento on?");
            chat.setStart(4);
            this.fcsd.updateChat(chat);
        } else if (chat.getStartStatus() == 4) {
            lesson = chat.getLesson();
            lesson.setLocation(msg);
            lesson.setCoordinates(LocationService.getCoordinates(msg));
            chat.setLesson(lesson);
            sendMessage(chat.getChatId(), "Milloin luentosi alkaa? Anna muodossa hh:mm dd (hh = tunti 00-23, mm = minuutti 00-59, dd = päivä Ma-Su)");
            chat.setStart(5);
            this.fcsd.updateChat(chat);
        } else if (chat.getStartStatus() == 5) {
            if (msg.split(" ").length > 1) {
                lesson = chat.getLesson();
                lesson.setStartTime(msg);
                chat.setLesson(lesson);
                sendMessage(chat.getChatId(), "Milloin luentosi loppuu? Anna muodossa hh:mm");
                chat.setStart(6);
                this.fcsd.updateChat(chat);
            } else {
                sendMessage(chat.getChatId(), "Anna aloitus ajankohta muodossa hh:mm dd (hh = tunti 00-23, mm = minuutti 00-59, dd = päivä Ma-Su)");
            }
        } else if (chat.getStartStatus() == 6) {
            lesson = chat.getLesson();
            lesson.setEndTime(msg);
            chat.setLesson(lesson);
            User user = udd.getUser(chat.getChatId());
            //System.out.println("First lesson of User: " + user.getLessons().get(0).toString());
            user.addLesson(lesson);
            udd.updateUser(user);
            sendMessage(chat.getChatId(), "Kiitos, luentosi on lisätty. Voit lisätä uuden luennon komennolla /lisaa. Voit tarkastella luentojasi komennolla /luennot");
            chat.setStart(2);
            this.fcsd.updateChat(chat);
        }
    }
    
    private void sendUserLessons(long chatId) {
        User user = udd.getUser(chatId);
        List<Lesson> lessons = user.getLessons();
        for (Lesson lesson : lessons) {
            sendMessage(chatId, lesson.toString());
            sendMessage(chatId, ItineraryService.getItinerary(user.getLocationString(), lesson.getLocationString(), "", lesson.getName()));
        }
    }
}