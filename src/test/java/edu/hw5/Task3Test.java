package edu.hw5;

import java.time.LocalDate;
import java.util.Optional;
import edu.hw5.Task3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class Task3Test {
    @ParameterizedTest
    @CsvSource(value = {
        "2023-09-08",
        "2023-9-8",
        "8/9/2023",
        "08/09/23",
        "62 days ago"
    })
    void parseDate(String date) {
        LocalDate localDate = LocalDate.of(2023, 9, 8);
        Assertions.assertEquals(Optional.of(localDate), Task3.parseDate(date));
    }

    @Test
    void parseDate() {
        LocalDate today = LocalDate.now();
        Assertions.assertEquals(Optional.of(today.plusDays(1)), Task3.parseDate("tomorrow"));
        Assertions.assertEquals(Optional.of(today), Task3.parseDate("today"));
        Assertions.assertEquals(Optional.of(today.minusDays(1)), Task3.parseDate("yesterday"));
    }

    @Test
    @DisplayName("Месяц больше 12")
    void parseDate_whenMonthMore12() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Task3.parseDate("12/60/2023");
        }, "Date is valid");

        Assertions.assertEquals("The month must not be more than the maximum value: 12", thrown.getMessage());
    }

    @Test
    @DisplayName("День больше 31")
    void parseDate_whenDayMore31() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Task3.parseDate("60/12/23");
        }, "Date is valid");

        Assertions.assertEquals("The day must not be more than the maximum value: 31", thrown.getMessage());
    }

    @ParameterizedTest
    @CsvSource(value = {
        "23-09-08",
        "2023--9-8",
        "8/9/-2023",
        "-8/09/23",
        "edde"
    })
    void parseDate_whenEmptyResult(String date) {
        Assertions.assertEquals(Optional.empty(), Task3.parseDate(date));
    }

    @Test
    @DisplayName("Null")
    void parseDate_whenNull() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Task3.parseDate(null);
        }, "Date is valid");

        Assertions.assertEquals("Date can not be null!", thrown.getMessage());
    }
}
