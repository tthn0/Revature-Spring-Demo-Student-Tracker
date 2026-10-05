package io.github.tthn0.demo.coach.components;

import org.springframework.stereotype.Component;

@Component
public class BasketballCoach implements Coach {
    @Override 
    public String getDailyWorkout() {
        return "Basketball";
    }
}
