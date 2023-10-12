package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task5 {
    private final static Logger LOGGER = LogManager.getLogger();

    private Task5() {

    }

    public static boolean isPalindromeDescendant(int num) {
        LOGGER.info("Number is: " + num);
        String strNum = Integer.toString(num);
        int length = strNum.length();
        if (length < 2) {
            return false;
        }

        for (int i = 0; i < (length / 2); i++) {
            if (strNum.charAt(i) != strNum.charAt(length - i - 1)) {
                if (length % 2 != 0) {
                    return false;
                }

                StringBuilder sumOfNum = new StringBuilder();
                for (int j = 0; j < length; j += 2) {
                    int temp =
                        Character.getNumericValue(strNum.charAt(j)) + Character.getNumericValue(strNum.charAt(j + 1));
                    sumOfNum.append(temp);
                }

                return isPalindromeDescendant(Integer.parseInt(sumOfNum.toString()));
            }
        }
        return true;
    }

}
