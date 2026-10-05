package io.github.tthn0.demo.coach.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.tthn0.demo.coach.components.Coach;

@RestController
@RequestMapping("/coach")
public class CoachController {
    // private static final Logger logger = LoggerFactory.getLogger(CoachController.class);

    private Coach baseballCoach, basketballCoach;

    @Value("${spring.application.name}")
    private String appName;

    @Autowired
    public CoachController(
        @Qualifier("baseballCoach") Coach baseballCoach,
        @Qualifier("basketballCoach") Coach basketballCoach
    ) {
        this.baseballCoach = baseballCoach;
        this.basketballCoach = basketballCoach;
    }
    
    @GetMapping({"", "/"})
    public String index() {
        // logger.info("/coach");
        return "Welcome to " + appName;
    }

    @GetMapping("/baseball")
    public String getBaseballCoach() {
        return baseballCoach.getDailyWorkout();
    }

    @GetMapping("/basketball")
    public String getBasketballCoach() {
        return basketballCoach.getDailyWorkout();
    }
}
