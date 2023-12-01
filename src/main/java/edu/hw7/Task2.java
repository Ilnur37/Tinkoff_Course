package edu.hw7;

import java.util.stream.LongStream;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Task2 {
    private final static int MAX_NUMBER = 20;

    public static long factorial(int n) {
        validateN(n);
        return LongStream.rangeClosed(1, n)
            .parallel()
            .reduce(1, (long a, long b) -> a * b);
    }

    private static void validateN(int n) {
        if (n > MAX_NUMBER) {
            throw new IllegalArgumentException("The program cannot count a value higher than 20!");
        }
        if (n < 0) {
            throw new IllegalArgumentException("Number can not be negative!");
        }
    }
}
