package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;
import lombok.NonNull;

public class Word extends Parse {
    private static final String WORD = "^\\w+$";

    @Override
    public Optional<LocalDate> parseDate(@NonNull String date) {
        if (!canParse(date, WORD)) {
            return nextOrEmpty(next, date);
        }
        LocalDate today = LocalDate.now();
        return switch (date) {
            case "tomorrow" -> Optional.of(today.plusDays(1));
            case "yesterday" -> Optional.of(today.minusDays(1));
            case "today" -> Optional.of(today);
            default -> Optional.empty();
        };
    }
}
