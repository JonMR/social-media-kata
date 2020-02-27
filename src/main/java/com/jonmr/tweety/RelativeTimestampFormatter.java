package com.jonmr.tweety;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.List;

public class RelativeTimestampFormatter {
    private static final Collection<ChronoUnit> CHRONO_UNITS = List.of(
            ChronoUnit.YEARS,
            ChronoUnit.MONTHS,
            ChronoUnit.DAYS,
            ChronoUnit.HOURS,
            ChronoUnit.MINUTES
    );
    private final LocalDateTime timestamp;
    private final LocalDateTime now;

    public RelativeTimestampFormatter(final LocalDateTime timestamp, final LocalDateTime now) {
        if (timestamp.isAfter(now)) {
            throw new IllegalArgumentException("The timestamp argument must be older than the now argument");
        }
        this.timestamp = timestamp;
        this.now = now;
    }

    public String format() {
        final ChronoUnit chronoUnit = CHRONO_UNITS
                .stream()
                .dropWhile(c -> c.between(timestamp, now) < 1)
                .findFirst()
                .orElse(ChronoUnit.SECONDS);
        final long interval = chronoUnit.between(timestamp, now);
        final String unit = chronoUnit.toString().toLowerCase();
        return String.format("%d %s", interval, interval == 1 ? unit.substring(0, unit.length() - 1) : unit);
    }
}
