package edu.hw7;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task2Test {
    @Test
    void factorial() {
        Assertions.assertAll(
            "isSubsequence_whenNull",
            () -> Assertions.assertEquals(1, Task2.factorial(0)),
            () -> Assertions.assertEquals(1, Task2.factorial(1)),
            () -> Assertions.assertEquals(2, Task2.factorial(2)),
            () -> Assertions.assertEquals(6, Task2.factorial(3)),
            () -> Assertions.assertEquals(24, Task2.factorial(4)),
            () -> Assertions.assertEquals(120, Task2.factorial(5)),
            () -> Assertions.assertEquals(720, Task2.factorial(6)),
            () -> Assertions.assertEquals(5040, Task2.factorial(7)),
            () -> Assertions.assertEquals(40320, Task2.factorial(8)),
            () -> Assertions.assertEquals(362880, Task2.factorial(9)),
            () -> Assertions.assertEquals(3628800, Task2.factorial(10)),
            () -> Assertions.assertEquals(39916800, Task2.factorial(11)),
            () -> Assertions.assertEquals(479001600, Task2.factorial(12))
        );
    }

    @Test
    void factorial_whenExArg() {
        IllegalArgumentException thrown1 = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Task2.factorial(-1);
        }, "Number is valid");
        IllegalArgumentException thrown2 = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Task2.factorial(21);
        }, "Number is valid");
        IllegalArgumentException thrown3 = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Task2.factorial(100);
        }, "Number is valid");
        Assertions.assertAll(
            () -> Assertions.assertEquals("Number can not be negative!", thrown1.getMessage()),
            () -> Assertions.assertEquals("The program cannot count a value higher than 20!", thrown2.getMessage()),
            () -> Assertions.assertEquals("The program cannot count a value higher than 20!", thrown3.getMessage())
        );
    }
}
