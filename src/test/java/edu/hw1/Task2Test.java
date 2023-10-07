package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    @DisplayName("Корректные данные")
    void countDigits() {
        int num = 12345;

        long countOfDigits = Task2.countDigits(num);

        assertThat(countOfDigits).isEqualTo(5);
    }

    @Test
    @DisplayName("Корректные данные(1 ицфра)")
    void countDigits_whenOneDigits() {
        int num = 0;

        long countOfDigits = Task2.countDigits(num);

        assertThat(countOfDigits).isEqualTo(1);
    }

    @Test
    @DisplayName("Число это Integer.MAX_VALUE")
    void countDigits_whenNumIsIntegerMAX_VALUE() {
        int num = Integer.MAX_VALUE;

        long countOfDigits = Task2.countDigits(num);

        assertThat(countOfDigits).isEqualTo(10);
    }
}
