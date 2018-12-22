/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.johanneslares.yliopistobot.threads;

import fi.johanneslares.yliopistobot.Itinerary;
import fi.johanneslares.yliopistobot.services.ItineraryService;
import fi.johanneslares.yliopistobot.Lesson;
import fi.johanneslares.yliopistobot.Message;
import fi.johanneslares.yliopistobot.User;
import fi.johanneslares.yliopistobot.Yliopistobotti;
import fi.johanneslares.yliopistobot.dao.FileMessageQueueDao;
import fi.johanneslares.yliopistobot.dao.FileUserDataDao;
import fi.johanneslares.yliopistobot.dao.MessageQueueDao;
import fi.johanneslares.yliopistobot.dao.UserDataDao;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * This class is responsible of fetching automatically itineraries
 * @author jlares
 */
public class AutoSender extends Thread {
    private Thread t;
    private String threadName;
    private UserDataDao udd = new FileUserDataDao();
    private long fetchBeforeArrive = 14400;
    private Yliopistobotti bot = new Yliopistobotti();
    private boolean test = false;

    /**
     * Create new AutoSender thread.
     * @param name thread name
     */
    public AutoSender(String name) {
        this.threadName = name;
        this.start();
    }
    
    /**
     * This is only for testing purposes
     * @param name Thread name
     * @param test if true, thread will be stopped shortly after start.
     */
    public AutoSender(String name, boolean test) {
        this.threadName = name;
        this.test = test;
        this.start();
    }
    
    /**
     * Start new thread. This method loops through every user and their lessons and gets itinerary if necessary.
     */
    @Override
    public void run() {        
        while (true) {
            try {
                List<User> users = udd.getUsers();
                this.loopThroughUsers(users);
                Thread.sleep(1000);
                if (test) {
                    break;
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private void loopThroughUsers(List<User> users) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm u");
        String date = sdf.format(new Date());        
        for (User user : users) {
            for (Lesson lesson : user.getLessons()) {
                if (lesson.getDayOfTheWeek() == Integer.parseInt(date.split(" ")[1])) {
                    this.getSuggestedRoute(lesson.getStartTimeHhMm(), user, lesson);
                }
            }
        }
    }
    
    /**
     * 
     * @param time Lesson start time
     * @param user User, which lesson it is
     * @param lesson Lesson, where we want to go
     */
    public void getSuggestedRoute(String time, User user, Lesson lesson) {
        int hours = Integer.parseInt(time.split(":")[0]);
        int min = Integer.parseInt(time.split(":")[1]);
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        Date calDate = cal.getTime();
        int day = cal.get(Calendar.DATE);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR) - 1900;
        Date date = new Date(year, month, day, hours, min);
        long unixTime = System.currentTimeMillis() / 1000L;
        long unixTime2 = date.getTime() / 1000L;
        if (unixTime + fetchBeforeArrive == unixTime2) {
            getRouteAndQueue(user, lesson);
        }
    }
    
    /**
     * 
     * @param user User to send itinerary suggestion
     * @param lesson Lesson, where user is going
     */
    public void getRouteAndQueue(User user, Lesson lesson) {
        Itinerary msg = ItineraryService.getItinerary(user.getLocationString(), lesson.getLocationString(), lesson.getStartTime().split(" ")[0], lesson.getName());
        MessageQueueDao mqd = new FileMessageQueueDao();
        mqd.addMessage(new Message(user.getChatId(), msg.getItinerary(), msg.getGotime()-1800L));        
    }
    
}
