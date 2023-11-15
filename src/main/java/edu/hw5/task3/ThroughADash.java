package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;
import lombok.NonNull;

public class ThroughADash extends Parse {
    private static final String THROUGH_A_DASH = "^\\d{4}-\\d{1,2}-\\d{1,2}$";

    @Override
    public Optional<LocalDate> parseDate(@NonNull String date) {
        if (!canParse(date, THROUGH_A_DASH)) {
            return nextOrEmpty(next, date);
        }
        String[] numbs = date.split("-");
        validateDateFormat(numbs);
        int year = Integer.parseInt(numbs[0]);
        int month = getInt(numbs[1]);
        int day = getInt(numbs[2]);
        validateDate(month, day);
        return Optional.of(LocalDate.of(year, month, day));
    }
}
