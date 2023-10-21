package edu.hw3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task1 {
    private static final Logger LOGGER = LogManager.getLogger();
    public static final int START_OF_ALPHABET_IN_UPPER = 65;
    public static final int END_OF_ALPHABET_IN_UPPER = 90;
    public static final int START_OF_ALPHABET_IN_LOWER = 97;
    public static final int END_OF_ALPHABET_IN_LOWER = 122;

    private Task1() {

    }

    public static String atbashCipher(String str) {
        LOGGER.info("String is: " + str);
        if (str == null) {
            throw new NullPointerException("String is can not be null!");
        }
        StringBuilder result = new StringBuilder();
        for (char ch : str.toCharArray()) {
            if (ch >= START_OF_ALPHABET_IN_UPPER && ch <= END_OF_ALPHABET_IN_UPPER) {
                result.append(changeChar(ch, true));
            } else if (ch >= START_OF_ALPHABET_IN_LOWER && ch <= END_OF_ALPHABET_IN_LOWER) {
                result.append(changeChar(ch, false));
            } else {
                result.append(ch);
            }
        }
        LOGGER.info("String after Atbash Cipher: " + result.toString());
        return result.toString();
    }

    private static char changeChar(char ch, boolean isUpperCase) {
        if (isUpperCase) {
            return (char) (END_OF_ALPHABET_IN_UPPER - (ch - START_OF_ALPHABET_IN_UPPER));
        } else {
            return (char) (END_OF_ALPHABET_IN_LOWER - (ch - START_OF_ALPHABET_IN_LOWER));
        }
    }
}
