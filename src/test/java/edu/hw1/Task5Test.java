package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {
    @Test
    @DisplayName("Корректные данные")
    void countDigits() {
        int num = 11211230;

        boolean result = Task5.isPalindromeDescendant(num);

        assertThat(result).isEqualTo(true);
    }

    @Test
    @DisplayName("Некорректные данные")
    void countDigits_whenIncorrectData() {
        int num = 1234;

        boolean result = Task5.isPalindromeDescendant(num);

        assertThat(result).isEqualTo(false);
    }

    @Test
    @DisplayName("Число состоит из одной цифры")
    void countDigits_whenNumberConsistsOfOneDigit() {
        int num = 1;

        boolean result = Task5.isPalindromeDescendant(num);

        assertThat(result).isEqualTo(false);
    }

    @Test
    @DisplayName("Число состоит из 2 цифр")
    void countDigits_whenNumberConsistsOfTwoDigit() {
        int num = 11;

        boolean result = Task5.isPalindromeDescendant(num);

        assertThat(result).isEqualTo(true);
    }

}
