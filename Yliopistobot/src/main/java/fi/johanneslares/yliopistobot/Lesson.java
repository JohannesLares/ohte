package fi.johanneslares.yliopistobot;

import java.util.HashMap;
import java.util.Map;

public class Lesson {
    private String location;
    private String name;
    private String startTime;
    private String endTime;
    private String coordinates;
    private Map<String, Integer> days;
    
    public Lesson(String location, String name, String start, String end, String coordinates) {
        this.location = location;
        this.coordinates = coordinates;
        this.name = name;
        this.startTime = start;
        this.endTime = end;
        this.days = new HashMap<>();
    }
    
    public Lesson() {
        this.location = "";
        this.name = "";
        this.coordinates = "";
        this.startTime = "";
        this.endTime = "";
        this.days = new HashMap<>();
    }
    
    public String getStartTime() {
        return this.startTime;
    }
    
    public String getEndTime() {
        return this.endTime;
    }
    
    public String getLocation() {
        return this.location;
    }
    
    public String getName() {
        return this.name;
    }   
    
    public int getDayOfTheWeek() {
        days.put("ma", 1);
        days.put("ti", 2);
        days.put("ke", 3);
        days.put("to", 4);
        days.put("pe", 5);
        days.put("la", 6);
        days.put("su", 7);
        String day = this.startTime.split(" ")[1].toLowerCase();
        return days.get(day);
    }
    
    public String getStartTimeHhMm() {
        return this.startTime.split(" ")[0];
    }
    
    public String getCoordinates() {
        return this.coordinates;
    }
    
    public String getLocationString() {
        return this.location + "::" + this.coordinates;
    }
    
    public void setCoordinates(String c) {
        this.coordinates = c;
    }
    
    public void setStartTime(String start) {
        this.startTime = start;
    }
    
    public void setEndTime(String end) {
        this.endTime = end;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    @Override
    public String toString() {
        return "Name: " + this.name + "\nLocation: " + this.location + "\nStart time: " + this.startTime + "\nEnd time: " + this.endTime;
    }
    
}
