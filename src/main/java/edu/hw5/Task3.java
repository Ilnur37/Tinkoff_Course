package edu.hw5;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task3 {
    private static final int MAX_MONTH = 12;
    private static final int MAX_DAY = 31;
    private static final int NOW_CENTURY = 2000;
    private static final int LAST_CENTURY = 1900;
    private static final int COUNT_NUMBS = 3;
    private static final String THROUGH_A_DASH = "^\\d{4}-\\d{1,2}-\\d{1,2}$";
    private static final String THROUGH_SLASH_4 = "^\\d{1,2}/\\d{1,2}/\\d{2,4}$";
    private static final String DAY_AGO = "^\\d+ (day|days) ago$";
    private static final String WORD = "^\\w+$";
    private static final String EMPTY = "Empty";

    private Task3() {

    }

    public static Optional<LocalDate> parseDate(String date) {
        if (date == null) {
            throw new IllegalArgumentException("Date can not be null!");
        }
        List<String> types = new ArrayList<>(List.of(THROUGH_A_DASH, THROUGH_SLASH_4, DAY_AGO, WORD));
        String typeParse = types.stream()
            .filter(type -> canParse(date, type))
            .findFirst().orElse(EMPTY);

        int year;
        int month;
        int day;
        switch (typeParse) {
            case THROUGH_A_DASH -> {
                String[] numbs = date.split("-");
                validationDateFormat(numbs);
                year = Integer.parseInt(numbs[0]);
                month = getInt(numbs[1]);
                day = getInt(numbs[2]);
            }
            case THROUGH_SLASH_4 -> {
                String[] numbs = date.split("/");
                validationDateFormat(numbs);
                day = getInt(numbs[0]);
                month = getInt(numbs[1]);
                year = Integer.parseInt(numbs[2]);
                if (numbs[2].length() == 2) {
                    if (year <= LocalDate.now().getYear() - NOW_CENTURY) {
                        year += NOW_CENTURY;
                    } else {
                        year += LAST_CENTURY;
                    }
                }
            }
            case DAY_AGO -> {
                LocalDate localDate = LocalDate.now();
                String[] numbs = date.split(" ");
                localDate = localDate.minusDays(Integer.parseInt(numbs[0]));
                return Optional.of(localDate);
            }
            case WORD -> {
                LocalDate today = LocalDate.now();
                return switch (date) {
                    case "tomorrow" -> Optional.of(today.plusDays(1));
                    case "yesterday" -> Optional.of(today.minusDays(1));
                    case "today" -> Optional.of(today);
                    default -> Optional.empty();
                };
            }
            default -> {
                return Optional.empty();
            }
        }
        validationDate(month, day);
        return Optional.of(LocalDate.of(year, month, day));
    }

    private static boolean canParse(String date, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
        return matcher.find();
    }

    private static int getInt(String num) {
        if (num.length() == 1) {
            return Integer.parseInt(num);
        }
        if (num.charAt(0) == '0') {
            return Character.getNumericValue(num.charAt(1));
        }
        return Integer.parseInt(num);
    }

    private static void validationDate(int month, int day) {
        validationMonth(month);
        validationDay(day);
    }

    private static void validationDateFormat(String[] numbs) {
        if (numbs.length != COUNT_NUMBS) {
            throw new IllegalArgumentException("Invalid date: " + Arrays.toString(numbs));
        }
    }

    private static void validationMonth(int month) {
        if (month > MAX_MONTH) {
            throw new IllegalArgumentException("The month must not be more than the maximum value: " + MAX_MONTH);
        }
    }

    private static void validationDay(int day) {
        if (day > MAX_DAY) {
            throw new IllegalArgumentException("The day must not be more than the maximum value: " + MAX_DAY);
        }
    }
}
