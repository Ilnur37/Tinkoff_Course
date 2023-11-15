package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;
import lombok.NonNull;

public class DayAgo extends Parse {
    private static final String DAY_AGO = "^\\d+ (day|days) ago$";

    @Override
    public Optional<LocalDate> parseDate(@NonNull String date) {
        if (!canParse(date, DAY_AGO)) {
            return nextOrEmpty(next, date);
        }
        LocalDate localDate = LocalDate.now();
        String[] numbs = date.split(" ");
        localDate = localDate.minusDays(Integer.parseInt(numbs[0]));
        return Optional.of(localDate);
    }
}
