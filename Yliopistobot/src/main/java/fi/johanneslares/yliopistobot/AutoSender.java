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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jlares
 */
public class AutoSender implements Runnable{
    private Thread t;
    private String threadName;
    private UserDataDao udd = new FileUserDataDao();

    public AutoSender(String name){
        this.threadName = name;
        this.start();
    }
    
    @Override
    public void run() {
        // This ain't the best algorithm I have written
        
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm FF");
        String startTime;
        String day;
        
        while (true) {
            try {
                String date = sdf.format(new Date());
                List<User> users = udd.getUsers();
                for (User user : users) {
                    for (Lesson lesson : user.getLessons()) {
                        if (lesson.getDayOfTheWeek().equals(date.split(" ")[1])) {
                            this.getSuggestedRoute(lesson.getStartTimeHhMm());
                        }
                    }
                }
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
   
    public void start () {
      //TODO this
        this.run();
    }
    
    public void getSuggestedRoute(String time){
        
    }
    
}
