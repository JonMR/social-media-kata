package com.jonmr.tweety;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.TemporalAmount;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class RelativeTimestampFormatterTest {
    private final String expectedOutput;
    private final TemporalAmount temporalAmount;
    private LocalDateTime now = LocalDateTime.now();

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "0 seconds", Duration.ofSeconds(0) },
                { "1 second", Duration.ofSeconds(1) },
                { "5 seconds", Duration.ofSeconds(5) },
                { "1 minute", Duration.ofSeconds(60) },
                { "5 minutes", Duration.ofMinutes(5) },
                { "5 hours", Duration.ofHours(5) },
                { "6 days", Duration.ofDays(6) },
                { "1 month", Period.ofMonths(1) },
                { "5 years", Period.ofYears(5) },
        });
    }

    public RelativeTimestampFormatterTest(String expectedOutput, TemporalAmount temporalAmount) {
        this.expectedOutput = expectedOutput;
        this.temporalAmount = temporalAmount;
    }

    @Test
    public void pastToPresent() {
        String formattedOutput = new RelativeTimestampFormatter(now.minus(temporalAmount), now).format();
        assertEquals(expectedOutput, formattedOutput);
    }

    @Test(expected = IllegalArgumentException.class)
    public void messagesCantTimeTravel() {
        // The extra second is to workaround the 0 seconds case
        new RelativeTimestampFormatter(now, now.minus(temporalAmount).minusSeconds(1));
    }
}