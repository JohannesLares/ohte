/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.johanneslares.yliopistobot;

import fi.johanneslares.yliopistobot.dao.FileUserDataDao;
import fi.johanneslares.yliopistobot.dao.UserDataDao;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jlares
 */
public class AutoSender implements Runnable {
    private Thread t;
    private String threadName;
    private UserDataDao udd = new FileUserDataDao();
    private long fetchBeforeArrive = 7200;
    private Yliopistobotti bot = new Yliopistobotti();

    public AutoSender(String name) {
        this.threadName = name;
        this.start();
    }
    
    @Override
    public void run() {
        System.out.println("Start");
        // This ain't the best algorithm I have written
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm u");
        
        while (true) {
            try {
                String date = sdf.format(new Date());
                List<User> users = udd.getUsers();
                for (User user : users) {
                    for (Lesson lesson : user.getLessons()) {
                        if (lesson.getDayOfTheWeek() == Integer.parseInt(date.split(" ")[1])) {
                            this.getSuggestedRoute(lesson.getStartTimeHhMm(), user, lesson);
                        } else {
                            //System.out.println(lesson.getDayOfTheWeek() + " " + date.split(" ")[1]);
                        }
                    }
                }
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void start() {
      //TODO this
        this.run();
    }
    
    public void getSuggestedRoute(String time, User user, Lesson lesson){
        int hours = Integer.parseInt(time.split(":")[0]);
        int min = Integer.parseInt(time.split(":")[1]);
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        Date calDate = cal.getTime();
        int day = cal.get(Calendar.DATE);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR)-1900;
        Date date = new Date(year, month, day, hours, min);
        System.out.println(date);
        long unixTime = System.currentTimeMillis() / 1000L;
        long unixTime2 = date.getTime() / 1000L;
        System.out.println(calDate);
        System.out.println(unixTime + " " + (unixTime2));
        if (unixTime + fetchBeforeArrive == unixTime2) {
            System.out.println((System.currentTimeMillis() + fetchBeforeArrive) + " | " + date.getTime());
            getRouteAndQueue(user, lesson);
        } else {
            System.out.println((unixTime + fetchBeforeArrive) + " | " + unixTime2);
        }
    }
    
    public void getRouteAndQueue(User user, Lesson lesson) {
        //TODO another thread to handle getItinerary.
        System.out.println("Kaksi tuntia aikaa, ennen kuin luento " + lesson.getName() + " alkaa chatissa " + user.getChatId());
        String msg = ItineraryService.getItinerary(user.getLocationString(), lesson.getLocationString(), lesson.getStartTime().split(" ")[0], lesson.getName());
        bot.sendMessage(user.getChatId(), msg);
    }
    
}
