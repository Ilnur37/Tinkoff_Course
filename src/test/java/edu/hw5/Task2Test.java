package edu.hw5;

import edu.hw5.Task2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Task2Test {
    @Test
    @DisplayName("Корректные данные(поиск пятниц)")
    void findFridays13_1() {
        int year = 1925;
        int day = 13;
        LocalDate date1 = LocalDate.of(year, 02,day);
        LocalDate date2 = LocalDate.of(year, 03,day);
        LocalDate date3 = LocalDate.of(year, 11,day);
        List<LocalDate> dates = new ArrayList<>(List.of(date1, date2, date3));
        Assertions.assertEquals(dates, Task2.findFridays13(year));
    }

    @Test
    @DisplayName("Корректные данные(поиск следующей пятницы)")
    void findFridays13_2() {
        int year = 1925;
        int day = 13;
        LocalDate date1 = LocalDate.of(year, 02,day);
        LocalDate date2 = LocalDate.of(year, 03,day);
        LocalDate date3 = LocalDate.of(year, 11,day);
        List<LocalDate> dates = new ArrayList<>(List.of(date1, date2, date3));

        Assertions.assertEquals(dates.get(2), Task2.nextFriday13(dates.get(1)));
        Assertions.assertEquals(dates.get(1), Task2.nextFriday13(dates.get(0)));
    }

    @Test
    @DisplayName("Год меньше 0")
    void findFridays13_whenYearLessThen0() {
        int year = -1;
        int day = 13;
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Task2.findFridays13(year);
        }, "Year is not less then 0");

        Assertions.assertEquals("The year must not be less than the minimum value: 0", thrown.getMessage());
    }
}
