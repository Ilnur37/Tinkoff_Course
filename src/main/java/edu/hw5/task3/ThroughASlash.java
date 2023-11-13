package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;
import lombok.NonNull;

public class ThroughASlash extends Parse {
    private static final int NOW_CENTURY = 2000;
    private static final int LAST_CENTURY = 1900;
    private static final String THROUGH_SLASH = "^\\d{1,2}/\\d{1,2}/\\d{2,4}$";

    @Override
    public Optional<LocalDate> parseDate(@NonNull String date) {
        if (!canParse(date, THROUGH_SLASH)) {
            return nextOrEmpty(next, date);
        }
        String[] numbs = date.split("/");
        validateDateFormat(numbs);
        int day = getInt(numbs[0]);
        int month = getInt(numbs[1]);
        int year = Integer.parseInt(numbs[2]);
        if (numbs[2].length() == 2) {
            if (year <= LocalDate.now().getYear() - NOW_CENTURY) {
                year += NOW_CENTURY;
            } else {
                year += LAST_CENTURY;
            }
        }
        validateDate(month, day);
        return Optional.of(LocalDate.of(year, month, day));
    }
}
