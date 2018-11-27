/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.johanneslares.yliopistobot;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author jlares
 */
public class UserTest {
    
    User user;
    
    @Before
    public void setUp(){
        this.user = new User(123);
    }
    
    @Test
    public void constructorCreatesNewUserWithCorrectId() {
        assertEquals(user.getChatId(), 123);
    }
    
    @Test
    public void lessonsWorkWithUser() {
        user.addLesson(new Lesson("Exactum", "TEST", "10:00 Ti", "12:00"));
        user.addLesson(new Lesson("Exactum2", "NewTest", "12:00 Ti", "12:30"));
        assertEquals(user.getLessons().get(0).getLocation(), "Exactum");
        assertEquals(user.getLessons().get(0).getName(), "TEST");
        assertEquals(user.getLessons().get(1).getLocation(), "Exactum2");
        assertEquals(user.getLessons().get(1).getStartTime(), "12:00 Ti");
        assertEquals(user.getLessons().get(1).getEndTime(), "12:30");
    }
    
    @Test
    public void setLessonsWorks() {
        List<Lesson> lessons = new ArrayList<>();
        lessons.add(new Lesson("Exactum3", "TEST1", "10:00 Ti", "12:00"));
        lessons.add(new Lesson("Exactum4", "NewTest", "12:00 Ti", "12:30"));
        user.setLessons(lessons);
        assertEquals(user.getLessons().get(0).getLocation(), "Exactum3");
        assertEquals(user.getLessons().get(0).getName(), "TEST1");
        assertEquals(user.getLessons().get(1).getLocation(), "Exactum4");
        assertEquals(user.getLessons().get(1).getStartTime(), "12:00 Ti");
        assertEquals(user.getLessons().get(1).getEndTime(), "12:30");
    }
    
    @Test
    public void homeLocationWorks() {
        user.setHomeLocation("Helsinki");
        assertEquals(user.getHomeLocation(), "Helsinki");
    }
    
    @Test
    public void toStringWorksCorrectly() {
        user.setHomeLocation("Lappeenranta");
        assertEquals(user.toString(), "123 : Lappeenranta");   
    }
    
    @Test
    public void setChatIdWorks() {
        user.setChatId(12345);
        assertEquals(user.getChatId(), 12345);
    }
    
}
