package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class Task6Test {
    @ParameterizedTest
    @CsvSource(value = {
        "abc, achfdbaabgabcaabg",
        "abc, ssassbsscs",
        "a, sssddda",
        "abc,abc"
    })
    void isSubsequence_true(String subsequence, String sequence) {
        Assertions.assertTrue(Task6.isSubsequence(subsequence, sequence));
    }

    @ParameterizedTest
    @CsvSource(value = {
        "abcz, achfdbaabgabcaabg",
        "abc, ssassbss",
        "a, sssddd",
        "abc,sdf"
    })
    void isSubsequence_false(String subsequence, String sequence) {
        Assertions.assertFalse(Task6.isSubsequence(subsequence, sequence));
    }

    @Test
    @DisplayName("Null")
    void isSubsequence_whenNull() {
        IllegalArgumentException thrown1 = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Task6.isSubsequence(null, "abc");
        }, "Strings is not null");
        IllegalArgumentException thrown2 = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Task6.isSubsequence("abc", null);
        }, "Strings is not null");
        IllegalArgumentException thrown3 = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Task6.isSubsequence(null, null);
        }, "Strings is not null");
        String result = "Parameters of function can not be null!";
        Assertions.assertAll(
            "isSubsequence_whenNull",
            () -> Assertions.assertEquals(result, thrown1.getMessage()),
            () -> Assertions.assertEquals(result, thrown2.getMessage()),
            () -> Assertions.assertEquals(result, thrown3.getMessage())
        );
    }
}
