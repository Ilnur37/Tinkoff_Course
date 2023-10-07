package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Корректные данные")
    void videoLengthInSeconds() {
        String time = "13:56";

        long seconds = Task1.minutesToSeconds(time);

        assertThat(seconds).isEqualTo(836);
    }

    @Test
    @DisplayName("Пробелы в строке")
    void videoLengthInSeconds_whenSpacesInStr() {
        String time = "1 3:56";

        long seconds = Task1.minutesToSeconds(time);

        assertThat(seconds).isEqualTo(-1);
    }

    @Test
    @DisplayName("Символы в строке")
    void videoLengthInSeconds_charInStr() {
        String time = "13:r3";

        long seconds = Task1.minutesToSeconds(time);

        assertThat(seconds).isEqualTo(-1);
    }

    @Test
    @DisplayName("Количество секунд больше 60")
    void videoLengthInSeconds_whenSecondMore60() {
        String time = "13:80";

        long seconds = Task1.minutesToSeconds(time);

        assertThat(seconds).isEqualTo(-1);
    }

    @Test
    @DisplayName("Количество секунд равно 60")
    void videoLengthInSeconds_whenSecondIs60() {
        String time = "13:60";

        long seconds = Task1.minutesToSeconds(time);

        assertThat(seconds).isEqualTo(-1);
    }

    @Test
    @DisplayName("Отрицательное время")
    void videoLengthInSeconds_whenTimeIsNegative() {
        String time = "-13:56";

        long seconds = Task1.minutesToSeconds(time);

        assertThat(seconds).isEqualTo(-1);
    }

    @Test
    @DisplayName("Строка null")
    void videoLengthInSeconds_shouldNullPointerException() {
        String time = null;

        NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> {
            Task1.minutesToSeconds(time);
        }, "String is not null");

        Assertions.assertEquals("String can not be null!", thrown.getMessage());
    }

    @Test
    @DisplayName("Формат числа больше Integer.MAX_VALUE")
    void videoLengthInSeconds_whenNumberFormGreaterIntegerMAX_VALUE() {
        String time = "12345678901:12";

        long seconds = Task1.minutesToSeconds(time);

        assertThat(seconds).isEqualTo(-1);
    }

    @Test
    @DisplayName("Результат больше Integer.MAX_VALUE")
    void videoLengthInSeconds_whenResultGreaterIntegerMAX_VALUE() {
        String time = "2147483647:59";

        long seconds = Task1.minutesToSeconds(time);

        assertThat(seconds).isEqualTo(Integer.MAX_VALUE * 60L + 59);
    }

}
