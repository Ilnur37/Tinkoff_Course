package edu.hw1;

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
    @DisplayName("Пограничное значение(172800)")
    void videoLengthInSeconds_whenSecondsIsMax() {
        String time = "2880:00";

        long seconds = Task1.minutesToSeconds(time);

        assertThat(seconds).isEqualTo(172800);
    }

    @Test
    @DisplayName("Выход за пределы 172800 секунд")
    void videoLengthInSeconds_whenSecondsIsMoreThenMax() {
        String time = "2880:01";

        long seconds = Task1.minutesToSeconds(time);

        assertThat(seconds).isEqualTo(-1);
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
    @DisplayName("Отрицательное время")
    void videoLengthInSeconds_whenTimeIsNegative() {
        String time = "-13:56";

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
    @DisplayName("Строка null")
    void videoLengthInSeconds_whenStrIsNull() {
        String time = null;

        long seconds = Task1.minutesToSeconds(time);

        assertThat(seconds).isEqualTo(-1);
    }

}
