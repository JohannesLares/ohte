/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.johanneslares.yliopistobot;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author jlares
 */
public class LessonTest {
    
    Lesson lesson;
    
    public LessonTest() {
    }
    
    @Before
    public void setUp() {
        lesson = new Lesson();
    }
    
    @Test
    public void emptyConstructorWorks() {
        assertEquals(lesson.getName(), "");
        assertEquals(lesson.getStartTime(), "");
    }
    
    @Test
    public void getStartTimeHhMmWorks() {
        lesson.setStartTime("10:00 Ma");
        assertEquals(lesson.getStartTimeHhMm(), "10:00");
    }
    
    @Test
    public void getCoordinatesWorks() {
        lesson.setCoordinates("60.123245,24.98765");
        assertEquals(lesson.getCoordinates(), "60.123245,24.98765");
    }
    
    @Test
    public void getLocationStringWorks() {
        lesson.setLocation("TEST123");
        lesson.setCoordinates("60.12345,24.12345");
        assertEquals(lesson.getLocationString(), "TEST123::60.12345,24.12345");
    }
    
    @Test
    public void setStartTimeWorks() {
        lesson.setStartTime("12:00 Ti");
        assertEquals(lesson.getStartTime(), "12:00 Ti");
    }
    
    @Test
    public void setEndTimeWorks() {
        lesson.setEndTime("12:30");
        assertEquals(lesson.getEndTime(), "12:30");
    }
    
    @Test
    public void setNameWorks() {
        lesson.setName("LinisTest");
        assertEquals(lesson.getName(), "LinisTest");
    }
    
    @Test
    public void setLocationWorks() {
        lesson.setLocation("TestPlace");
        assertEquals(lesson.getLocation(), "TestPlace");
    }
    
    @Test
    public void toStringWorks() {
        lesson.setLocation("Helsinki");
        lesson.setName("Linis");
        lesson.setStartTime("13:00 Ti");
        lesson.setEndTime("15:00");
        assertEquals(lesson.toString(), "Name: Linis\nLocation: Helsinki\nStart time: 13:00 Ti\nEnd time: 15:00");
    }
    
    @Test
    public void getDayOfTheWeekWorks() {
        lesson.setStartTime("10:00 Ke");
        assertEquals(lesson.getDayOfTheWeek(), 3);
    }
}
