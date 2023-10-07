package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {
    @Test
    @DisplayName("Корректные данные сдвиг влево")
    void rotateLeft() {
        int n = 25;
        int shift = 2;

        int result = Task7.rotateLeft(n, shift);

        assertThat(result).isEqualTo(7);
    }

    @Test
    @DisplayName("Корректные данные сдвиг вправо")
    void rotateRight() {
        int n = 25;
        int shift = 2;

        int result = Task7.rotateRight(n, shift);

        assertThat(result).isEqualTo(14);
    }

    @Test
    @DisplayName("Сдвиг вправо, но shift < 0")
    void rotateRight_whenShiftLess0() {
        int n = 25;
        int shift = -2;

        int result = Task7.rotateRight(n, shift);

        assertThat(result).isEqualTo(7);
    }

    @Test
    @DisplayName("Сдвиг влево, но shift < 0")
    void rotateLeft_whenShiftLess0() {
        int n = 25;
        int shift = -2;

        int result = Task7.rotateLeft(n, shift);

        assertThat(result).isEqualTo(14);
    }

    @Test
    @DisplayName("Сдвиг больше длинны числа в 2СС")
    void rotateRight_whenShiftMoreThenLengthNum() {
        int n = 25;
        int shift = 10;

        int result = Task7.rotateRight(n, shift);

        assertThat(result).isEqualTo(25);
    }

    @Test
    @DisplayName("Отрицательное число")
    void countK_whenRuntimeExceptionLess0() {
        int n = -25;
        int shift = 2;

        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> {
            Task7.rotateRight(n, shift);
        }, "Number is valid");

        Assertions.assertEquals("The number must be greater than zero!", thrown.getMessage());
    }

    @Test
    @DisplayName("Число равно 0")
    void countK_whenRuntimeExceptionEquals0() {
        int n = 0;
        int shift = 2;

        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> {
            Task7.rotateRight(n, shift);
        }, "Number is valid");

        Assertions.assertEquals("The number must be greater than zero!", thrown.getMessage());
    }
}
