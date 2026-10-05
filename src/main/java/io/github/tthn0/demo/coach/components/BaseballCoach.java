package io.github.tthn0.demo.coach.components;

import org.springframework.stereotype.Component;

@Component
public class BaseballCoach implements Coach {
    @Override 
    public String getDailyWorkout() {
        return "Baseball";
    }
}
