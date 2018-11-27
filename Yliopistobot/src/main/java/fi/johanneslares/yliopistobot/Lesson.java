package fi.johanneslares.yliopistobot;

public class Lesson {
    private String location;
    private String name;
    private String startTime;
    private String endTime;
    
    public Lesson(String location, String name, String start, String end) {
        this.location = location;
        this.name = name;
        this.startTime = start;
        this.endTime = end;
    }
    
    public Lesson() {
        this.location = "";
        this.name = "";
        this.startTime = "";
        this.endTime = "";
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
