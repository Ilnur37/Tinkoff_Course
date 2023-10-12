package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    @Test
    @DisplayName("Корректные данные")
    void isNestable() {
        int[] arr1 = new int[] {1, 2, 3, 4};
        int[] arr2 = new int[] {0, 6};

        boolean result = Task3.isNestable(arr1, arr2);

        assertThat(result).isEqualTo(true);
    }

    @Test
    @DisplayName("min(a1) < min(a2)")
    void isNestable_whenMinA1lessMinA2() {
        int[] arr1 = new int[] {1, 2, 3, 4};
        int[] arr2 = new int[] {2, 6};

        boolean result = Task3.isNestable(arr1, arr2);

        assertThat(result).isEqualTo(false);
    }

    @Test
    @DisplayName("min(a1) = min(a2)")
    void isNestable_whenMinA1equalsMinA2() {
        int[] arr1 = new int[] {1, 2, 3, 4};
        int[] arr2 = new int[] {1, 6};

        boolean result = Task3.isNestable(arr1, arr2);

        assertThat(result).isEqualTo(false);
    }

    @Test
    @DisplayName("max(a1) < max(a2)")
    void isNestable_whenMaxA1lessMaxA2() {
        int[] arr1 = new int[] {1, 2, 3, 7};
        int[] arr2 = new int[] {0, 6};

        boolean result = Task3.isNestable(arr1, arr2);

        assertThat(result).isEqualTo(false);
    }

    @Test
    @DisplayName("max(a1) = max(a2)")
    void isNestable_whenMaxA1equalsMaxA2() {
        int[] arr1 = new int[] {1, 2, 3, 6};
        int[] arr2 = new int[] {0, 6};

        boolean result = Task3.isNestable(arr1, arr2);

        assertThat(result).isEqualTo(false);
    }

    @Test
    @DisplayName("В первом массиве 1 элемент")
    void isNestable_whenFirstArrHasOneElement() {
        int[] arr1 = new int[] {1};
        int[] arr2 = new int[] {0, 6};

        boolean result = Task3.isNestable(arr1, arr2);

        assertThat(result).isEqualTo(true);
    }

    @Test
    @DisplayName("В первом массиве 0 элементов")
    void videoLengthInSeconds_shouldRuntimeException1() {
        int[] arr1 = new int[] {};
        int[] arr2 = new int[] {0, 6};

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Task3.isNestable(arr1, arr2);
        }, "There is not 0 element in Array1");

        Assertions.assertEquals("The length of the first array must be greater than 0!", thrown.getMessage());
    }

    @Test
    @DisplayName("Во втором массиве 1 элемент")
    void videoLengthInSeconds_shouldRuntimeException2() {
        int[] arr1 = new int[] {1, 2, 3, 6};
        int[] arr2 = new int[] {0};

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Task3.isNestable(arr1, arr2);
        }, "There is not 1 element in Array2");

        Assertions.assertEquals("The length of the second array must be greater than 2!", thrown.getMessage());
    }

}
