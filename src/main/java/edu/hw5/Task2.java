package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class Task2 {
    private final static int MIN_YEAR = 0;
    private final static int THIRTEENTH = 13;

    private Task2() {

    }

    public static List<LocalDate> findFridays13(int year) {
        if (year < MIN_YEAR) {
            throw new IllegalArgumentException("The year must not be less than the minimum value: " + MIN_YEAR);
        }
        List<LocalDate> fridays13 = new ArrayList<>();
        LocalDate date = LocalDate.of(year, 1, THIRTEENTH);
        while (date.getYear() == year) {
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                fridays13.add(date);
            }
            date = date.plusMonths(1);
        }
        return fridays13;
    }

    public static LocalDate nextFriday13(LocalDate date) {
        LocalDate nextFriday13 = date.plusDays(1);
        while (!nextFriday13.getDayOfWeek().equals(DayOfWeek.FRIDAY) || nextFriday13.getDayOfMonth() != THIRTEENTH) {
            nextFriday13 = nextFriday13.plusMonths(1);
            nextFriday13 = nextFriday13
                .withDayOfMonth(THIRTEENTH)
                .with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
        }

        return nextFriday13;
    }
}
