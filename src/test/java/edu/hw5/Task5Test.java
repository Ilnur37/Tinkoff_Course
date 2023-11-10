package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class Task5Test {
    @ParameterizedTest
    @CsvSource(value = {
        "А123ВЕ777",
        "А123ВЕ77",
        "А023ВЕ777"
    })
    void matchNumber_true(String number) {
        Assertions.assertTrue(Task5.matchNumber(number));
    }

    @ParameterizedTest
    @CsvSource(value = {
        "А123ВЕ077",
        "А123ВЕ07",
        "А023ФЕ777",
        "АА123ВЕ777",
        "А1223ВЕ777",
        "А13ВЕ777",
    })
    void matchNumber_false(String number) {
        Assertions.assertFalse(Task5.matchNumber(number));
    }

    @Test
    @DisplayName("Null")
    void matchNumber_whenNull() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Task5.matchNumber(null);
        }, "Number is valid");

        Assertions.assertEquals("Number can not be null!", thrown.getMessage());
    }
}
