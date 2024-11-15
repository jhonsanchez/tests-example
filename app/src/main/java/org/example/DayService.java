package org.example;

import java.util.Random;

public class DayService {
    private static final String[] DAYS_OF_WEEK = {
            "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
    };

    public String getDay() {
        Random random = new Random();
        int index = random.nextInt(DAYS_OF_WEEK.length);
        return DAYS_OF_WEEK[index];
    }
}
