package edu.hw3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task4 {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int[] ARABIC_NUMBERS = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] ROMAN_SYMBOLS =
        {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    private static final int MAX_VALUE = 3999;

    private Task4() {

    }

    public static String convertToRoman(int num) {
        LOGGER.info("Number is: " + num);
        if (num > MAX_VALUE) {
            throw new IllegalArgumentException("The number must be less than 4000!");
        }
        int tempNum = num;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ARABIC_NUMBERS.length; i++) {
            while (ARABIC_NUMBERS[i] <= tempNum) {
                tempNum -= ARABIC_NUMBERS[i];
                sb.append(ROMAN_SYMBOLS[i]);
            }
        }
        LOGGER.info("Number in roman is: " + sb.toString());
        return sb.toString();
    }
}
