package edu.hw5;

import edu.hw5.Task4;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class Task4Test {
    @ParameterizedTest
    @CsvSource(value = {
        "!qwerty",
        "q@w#erty",
        "qwe$rty",
        "qwer%ty",
        "qwert^y&",
        "qwerty&",
        "qwerty*yg"
    })
    void matchPassword_true(String password) {
        Assertions.assertTrue(Task4.matchPassword(password));
    }

    @ParameterizedTest
    @CsvSource(value = {
        "qwerty",
        "q_werty",
        "qwe.rty",
        "qwerty,"
    })
    void matchPassword_false(String password) {
        Assertions.assertFalse(Task4.matchPassword(password));
    }

    @Test
    @DisplayName("Null")
    void matchPassword_whenNull() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Task4.matchPassword(null);
        }, "Password is valid");

        Assertions.assertEquals("Password can not be null!", thrown.getMessage());
    }
}
