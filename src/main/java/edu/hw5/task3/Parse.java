package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.NonNull;

public abstract class Parse {
    private static final int MAX_MONTH = 12;
    private static final int MAX_DAY = 31;
    private static final int COUNT_NUMBS = 3;
    protected Parse next;

    public void setNext(Parse next) {
        this.next = next;
    }

    public abstract Optional<LocalDate> parseDate(@NonNull String date);

    public boolean canParse(String date, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
        return matcher.find();
    }

    Optional<LocalDate> nextOrEmpty(Parse next, String date) {
        if (next != null) {
            return next.parseDate(date);
        } else {
            return Optional.empty();
        }
    }

    static int getInt(String num) {
        if (num.length() == 1) {
            return Integer.parseInt(num);
        }
        if (num.charAt(0) == '0') {
            return Character.getNumericValue(num.charAt(1));
        }
        return Integer.parseInt(num);
    }

    static void validateDate(int month, int day) {
        validateMonth(month);
        validateDay(day);
    }

    static void validateDateFormat(String[] numbs) {
        if (numbs.length != COUNT_NUMBS) {
            throw new IllegalArgumentException("Invalid date: " + Arrays.toString(numbs));
        }
    }

    static void validateMonth(int month) {
        if (month > MAX_MONTH) {
            throw new IllegalArgumentException("The month must not be more than the maximum value: " + MAX_MONTH);
        }
    }

    static void validateDay(int day) {
        if (day > MAX_DAY) {
            throw new IllegalArgumentException("The day must not be more than the maximum value: " + MAX_DAY);
        }
    }
}
