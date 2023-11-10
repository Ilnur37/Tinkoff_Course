package edu.hw5;

import java.util.ArrayList;
import java.util.List;

import edu.hw5.Task1;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task1Test {
    @Test
    @DisplayName("Корректные данныe")
    void averageTime() {
        List<String> strings = new ArrayList<>();
        strings.add("2022-03-12, 20:20 - 2022-03-12, 23:50");
        strings.add("2022-04-01, 21:30 - 2022-04-02, 01:20");
        Assertions.assertEquals("3ч 40м", Task1.averageTime(strings));
    }

    @Test
    @DisplayName("1 интервал времни нулевой")
    void averageTime_whenOneIntervalZero() {
        List<String> strings = new ArrayList<>();
        strings.add("2022-03-12, 20:20 - 2022-03-12, 23:50");
        strings.add("2022-04-01, 21:30 - 2022-04-02, 01:20");
        strings.add("2022-03-12, 20:20 - 2022-03-12, 20:20");
        Assertions.assertEquals("2ч 26м", Task1.averageTime(strings));
    }

    @Test
    @DisplayName("Пустой список")
    void averageTime_whenListIsEmpty() {
        List<String> strings = new ArrayList<>();
        Assertions.assertEquals("0ч 00м", Task1.averageTime(strings));
    }

    @Test
    @DisplayName("Список null")
    void averageTime_whenListIsNull() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Task1.averageTime(null);
        }, "List is not null");

        Assertions.assertEquals("List of date can not be null!", thrown.getMessage());
    }

    @Test
    @DisplayName("В списке есть null элементы")
    void averageTime_whenListHasNullElement() {
        List<String> strings = new ArrayList<>();
        strings.add("2022-03-12, 20:20 - 2022-03-12, 23:50");
        strings.add(null);
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Task1.averageTime(strings);
        }, "All elements of the list are not null");

        Assertions.assertEquals("Element of list of date can not be null!", thrown.getMessage());
    }

    @Test
    @DisplayName("Синтаксичкчки неверный интервал времени")
    void averageTime_whenTimeIntervalIncorrectly() {
        List<String> strings = new ArrayList<>();
        strings.add("2022-03-12, 20:20 - 2022-03-12, 23:50 - 2022-03-12, 23:50");
        strings.add("2022-04-01, 21:30 - 2022-04-02, 01:20");
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Task1.averageTime(strings);
        }, "All elements of the list are correctly");

        Assertions.assertEquals("Time interval specified incorrectly!", thrown.getMessage());
    }

    @Test
    @DisplayName("Неверный формат времени")
    void averageTime_whenTimeFormatIncorrectly() {
        List<String> strings = new ArrayList<>();
        strings.add("2022/03/12, 20/20 - 2022-03-12, 23:50");
        strings.add("2022-04-01, 21:30 - 2022-04-02, 01:20");
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Task1.averageTime(strings);
        }, "All elements of the list are correctly");

        Assertions.assertEquals(
            "Text 2022/03/12, 20/20 does not match the format ^\\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2}$!",
            thrown.getMessage()
        );
    }

    @Test
    @DisplayName("Отрицательный интервал времени")
    void averageTime_whenNegativeInterval() {
        List<String> strings = new ArrayList<>();
        strings.add("2022-03-12, 20:20 - 2022-03-12, 20:00");
        strings.add("2022-04-01, 21:30 - 2022-04-02, 01:20");
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Task1.averageTime(strings);
        }, "All elements of the list are correctly");

        Assertions.assertEquals(
            "Text [2022-03-12, 20:20, 2022-03-12, 20:00], time interval cannot be negative!",
            thrown.getMessage()
        );
    }
}
