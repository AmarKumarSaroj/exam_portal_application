package com.onlineExamination.entity;
import java.time.Duration;
import java.time.LocalTime;

public class TimeDifferenceExample {
    public static void main(String[] args) {
        LocalTime currentTime = LocalTime.now();
        LocalTime endTime = LocalTime.of(18, 0); // example end time is 6:00 PM
        
        Duration duration = Duration.between(currentTime, endTime);
        long hours = duration.toHours(); // get the difference in hours
        long minutes = duration.toMinutes() % 60;
        long seconds = duration.toSeconds() % 60;// get the difference in minutes
        
        System.out.printf("Time remaining: %d hours and %d minutes", hours +":"+ minutes +":"+seconds);
    }
}
