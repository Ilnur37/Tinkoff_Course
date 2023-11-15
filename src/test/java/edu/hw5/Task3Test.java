package edu.hw5;

import edu.hw5.task3.DayAgo;
import edu.hw5.task3.Parse;
import edu.hw5.task3.ThroughADash;
import edu.hw5.task3.ThroughASlash;
import edu.hw5.task3.Word;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class Task3Test {

    private Parse createChainOfResponsibility() {
        Parse throughADash = new ThroughADash();
        Parse throughASlash = new ThroughASlash();
        Parse dayAgo = new DayAgo();
        Parse word = new Word();
        dayAgo.setNext(word);
        throughASlash.setNext(dayAgo);
        throughADash.setNext(throughASlash);
        return throughADash;
    }

    @ParameterizedTest
    @CsvSource(value = {
        "2023-09-08",
        "2023-9-8",
        "8/9/2023",
        "08/09/23"
    })
    void parseDate(String date) {
        Parse parse = createChainOfResponsibility();
        LocalDate localDate = LocalDate.of(2023, 9, 8);
        Assertions.assertEquals(Optional.of(localDate), parse.parseDate(date));
    }

    @Test
    void parseDate() {
        Parse parse = createChainOfResponsibility();
        LocalDate today = LocalDate.now();
        Assertions.assertEquals(Optional.of(today.plusDays(1)), parse.parseDate("tomorrow"));
        Assertions.assertEquals(Optional.of(today), parse.parseDate("today"));
        Assertions.assertEquals(Optional.of(today.minusDays(1)), parse.parseDate("yesterday"));
    }

    @Test
    @DisplayName("Месяц больше 12")
    void parseDate_whenMonthMore12() {
        Parse parse = createChainOfResponsibility();
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            parse.parseDate("12/60/2023");
        }, "Date is valid");

        Assertions.assertEquals("The month must not be more than the maximum value: 12", thrown.getMessage());
    }

    @Test
    @DisplayName("День больше 31")
    void parseDate_whenDayMore31() {
        Parse parse = createChainOfResponsibility();
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            parse.parseDate("60/12/23");
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
        Parse parse = createChainOfResponsibility();
        Assertions.assertEquals(Optional.empty(), parse.parseDate(date));
    }

    @Test
    @DisplayName("Null")
    void parseDate_whenNull() {
        Parse parse = createChainOfResponsibility();
        NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> {
            parse.parseDate(null);
        }, "Date is valid");

        Assertions.assertEquals("date is marked non-null but is null", thrown.getMessage());
    }
}
