package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task1 {
    private final static int SECONDS_IN_HOUR = 3600;
    private final static int SECONDS_IN_MINUTE = 60;
    private final static String TEXT = "Text ";

    private Task1() {

    }

    public static String averageTime(List<String> dates) {
        validationListDates(dates);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");

        long totalSeconds = 0;
        for (String session : dates) {
            String[] times = session.split(" - ");
            validationTime(times);
            LocalDateTime start = LocalDateTime.parse(times[0], formatter);
            LocalDateTime end = LocalDateTime.parse(times[1], formatter);

            long interval = Duration.between(start, end).getSeconds();
            if (interval < 0) {
                throw new IllegalArgumentException(
                    TEXT + Arrays.toString(times) + ", time interval cannot be negative!");
            }
            totalSeconds += interval;
        }

        long averageSeconds = 0;
        if (dates.size() != 0) {
            averageSeconds = totalSeconds / dates.size();
        }
        long hours = averageSeconds / SECONDS_IN_HOUR;
        long minutes = (averageSeconds % SECONDS_IN_HOUR) / SECONDS_IN_MINUTE;

        return String.format("%dч %02dм", hours, minutes);
    }

    private static void validationListDates(List<String> dates) {
        if (dates == null) {
            throw new IllegalArgumentException("List of date can not be null!");
        }
        for (String date : dates) {
            if (date == null) {
                throw new IllegalArgumentException("Element of list of date can not be null!");
            }
        }
    }

    private static void validationTime(String[] times) {
        if (times.length != 2) {
            throw new IllegalArgumentException("Time interval specified incorrectly!");
        }
        Pattern pattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2}$");
        for (String time : times) {
            Matcher matcher = pattern.matcher(time);
            if (!matcher.find()) {
                throw new IllegalArgumentException(
                    TEXT + time + " does not match the format " + pattern.pattern() + "!");
            }
        }
    }
}
