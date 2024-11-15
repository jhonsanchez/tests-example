package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.mockito.AdditionalAnswers.answersWithDelay;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IntegrationTests {
    @Mock
    private DayService dayService;

    @Test
    void givenDayServiceLongerThan30Sec_thenIsNotFriday() {
        when(dayService.getDay()).then(answersWithDelay(60000, invocation -> "Monday"));
        UnitTestApp integrationTestApp = new UnitTestApp(dayService);
        String expectedResult = "Nope";
        assertTimeout(Duration.ofSeconds(35), () -> {
            String actualResult = integrationTestApp.isFridayWithTimeout();
            assertEquals(expectedResult, actualResult);
        });
    }
}