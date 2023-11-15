package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.NonNull;

public class Task1 {
    private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");
    private final static Pattern PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2}$");

    private Task1() {

    }

    public static String averageTime(@NonNull List<String> dates) {
        validateListDates(dates);

        long totalSeconds = 0;
        for (String session : dates) {
            String[] times = session.split(" - ");
            validateTime(times);
            LocalDateTime start = LocalDateTime.parse(times[0], FORMATTER);
            LocalDateTime end = LocalDateTime.parse(times[1], FORMATTER);

            long interval = Duration.between(start, end).getSeconds();
            if (interval < 0) {
                throw new IllegalArgumentException(
                    Arrays.toString(times) + ", time interval cannot be negative!");
            }
            totalSeconds += interval;
        }

        Duration time = Duration.ofSeconds(0);
        if (dates.size() != 0) {
            time = time.plusSeconds(totalSeconds / dates.size());
        }
        long hours = time.toHours();
        long minutes = time.toMinutesPart();
        return String.format("%dч %02dм", hours, minutes);
    }

    private static void validateListDates(List<String> dates) {
        for (String date : dates) {
            if (date == null) {
                throw new IllegalArgumentException("Element of list of date can not be null!");
            }
        }
    }

    private static void validateTime(String[] times) {
        if (times.length != 2) {
            throw new IllegalArgumentException("Time interval specified incorrectly!");
        }
        for (String time : times) {
            Matcher matcher = PATTERN.matcher(time);
            if (!matcher.find()) {
                throw new IllegalArgumentException(
                    time + " does not match the format " + PATTERN.pattern() + "!");
            }
        }
    }
}
