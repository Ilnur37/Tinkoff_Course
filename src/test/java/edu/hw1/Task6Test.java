package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {
    @Test
    @DisplayName("Корректные данные")
    void countK() {
        int k = 6621;

        int result = Task6.countK(k);

        assertThat(result).isEqualTo(5);
    }

    @Test
    @DisplayName("Длинна числа больше 4")
    void countK_whenLengthOfNumberMoreThenFive() {
        int k = 11111;

        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> {
            Task6.countK(k);
        }, "Number is valid");

        Assertions.assertEquals("The number k must be in the range [1001, 9999]!", thrown.getMessage());
    }

    @Test
    @DisplayName("Длинна числа меньше 4")
    void countK_whenLengthOfNumberLessThenFour() {
        int k = 111;

        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> {
            Task6.countK(k);
        }, "Number is valid");

        Assertions.assertEquals("The number k must be in the range [1001, 9999]!", thrown.getMessage());
    }

    @Test
    @DisplayName("Число равно 1000")
    void countK_whenNumberIs1000() {
        int k = 1000;

        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> {
            Task6.countK(k);
        }, "Number is valid");

        Assertions.assertEquals("The number k must be in the range [1001, 9999]!", thrown.getMessage());
    }

    @Test
    @DisplayName("Число равно 5555")
    void countK_whenNumberConsists4IdenticalNumbers() {
        int k = 5555;

        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> {
            Task6.countK(k);
        }, "Number is valid");

        Assertions.assertEquals("The number must not consist of 4 identical numbers!", thrown.getMessage());
    }
}
