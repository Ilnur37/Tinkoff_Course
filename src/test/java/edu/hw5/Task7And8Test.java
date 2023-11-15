package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class Task7And8Test {
    @ParameterizedTest
    @CsvSource(value = {
        "11011",
        "100110",
        "00000"
    })
    void task7_1_true(String str) {
        Assertions.assertTrue(Task7And8.task7_1(str));
    }

    @ParameterizedTest
    @CsvSource(value = {
        "1",
        "11",
        "111",
        "00111"
    })
    void task7_1_false(String str) {
        Assertions.assertFalse(Task7And8.task7_1(str));
    }

    @ParameterizedTest
    @CsvSource(value = {
        "11011",
        "11",
        "00000",
        "01010"
    })
    void task7_2_true(String str) {
        Assertions.assertTrue(Task7And8.task7_2(str));
    }

    @ParameterizedTest
    @CsvSource(value = {
        "1",
        "10",
        "110",
        "00111"
    })
    void task7_2_false(String str) {
        Assertions.assertFalse(Task7And8.task7_2(str));
    }

    @ParameterizedTest
    @CsvSource(value = {
        "1",
        "11",
        "111",
        "101"
    })
    void task7_3_true(String str) {
        Assertions.assertTrue(Task7And8.task7_3(str));
    }

    @ParameterizedTest
    @CsvSource(value = {
        "1111",
        "11111",
        "10101"
    })
    void task7_3_false(String str) {
        Assertions.assertFalse(Task7And8.task7_3(str));
    }

    @ParameterizedTest
    @CsvSource(value = {
        "1",
        "111",
        "10101",
        "1111100"
    })
    void task8_1_true(String str) {
        Assertions.assertTrue(Task7And8.task8_1(str));
    }

    @ParameterizedTest
    @CsvSource(value = {
        "11",
        "1010",
        "000011"
    })
    void task8_1_false(String str) {
        Assertions.assertFalse(Task7And8.task8_1(str));
    }

    @ParameterizedTest
    @CsvSource(value = {
        "010",
        "01001",
        "101011",
        "11010110"
    })
    void task8_2_true(String str) {
        Assertions.assertTrue(Task7And8.task8_2(str));
    }

    @ParameterizedTest
    @CsvSource(value = {
        "01",
        "0010",
        "10101",
        "1001010"
    })
    void task8_2_false(String str) {
        Assertions.assertFalse(Task7And8.task8_2(str));
    }

    @ParameterizedTest
    @CsvSource(value = {
        "000",
        "01001000",
        "000100100001"
    })
    void task8_3_true(String str) {
        Assertions.assertTrue(Task7And8.task8_3(str));
    }

    @ParameterizedTest
    @CsvSource(value = {
        "01",
        "00010",
        "10101",
        "1001010"
    })
    void task8_3_false(String str) {
        Assertions.assertFalse(Task7And8.task8_3(str));
    }

    @ParameterizedTest
    @CsvSource(value = {
        "000",
        "1101001",
        "01001",
        "101001"
    })
    void task8_4_true(String str) {
        Assertions.assertTrue(Task7And8.task8_4(str));
    }

    @ParameterizedTest
    @CsvSource(value = {
        "11",
        "111"
    })
    void task8_4_false(String str) {
        Assertions.assertFalse(Task7And8.task8_4(str));
    }

    @ParameterizedTest
    @CsvSource(value = {
        "010",
        "0101",
        "01011101",
        "1111110"
    })
    void task8_5_true(String str) {
        Assertions.assertTrue(Task7And8.task8_5(str));
    }

    @ParameterizedTest
    @CsvSource(value = {
        "0",
        "0000",
        "100101",
        "1001010"
    })
    void task8_5_false(String str) {
        Assertions.assertFalse(Task7And8.task8_5(str));
    }

    @ParameterizedTest
    @CsvSource(value = {
        "010101",
        "00000",
        "10001001",
        "0101010000"
    })
    void task8_6_true(String str) {
        Assertions.assertTrue(Task7And8.task8_6(str));
    }

    @ParameterizedTest
    @CsvSource(value = {
        "11",
        "100110",
        "10101011",
        "0101010101001111100"
    })
    void task8_6_false(String str) {
        Assertions.assertFalse(Task7And8.task8_6(str));
    }

    @ParameterizedTest
    @CsvSource(value = {
        "010101",
        "010101010",
        "10001001",
        "0101010000"
    })
    void task8_7_true(String str) {
        Assertions.assertTrue(Task7And8.task8_7(str));
    }

    @ParameterizedTest
    @CsvSource(value = {
        "11",
        "100110",
        "101101011",
        "0101010101001111100"
    })
    void task8_7_false(String str) {
        Assertions.assertFalse(Task7And8.task8_7(str));
    }
}
