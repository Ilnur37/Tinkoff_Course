package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task2 {
    private final static Logger LOGGER = LogManager.getLogger();

    private Task2() {

    }

    @SuppressWarnings("MagicNumber")
    public static int countDigits(int num) {
        LOGGER.info("Number is: " + num);
        int copyNum = Math.abs(num);
        int length = 1;
        if (copyNum >= 100000000) {
            length += 8;
            copyNum /= 100000000;
        }
        if (copyNum >= 10000) {
            length += 4;
            copyNum /= 10000;
        }
        if (copyNum >= 100) {
            length += 2;
            copyNum /= 100;
        }
        if (copyNum >= 10) {
            length += 1;
        }
        return length;
    }
}
