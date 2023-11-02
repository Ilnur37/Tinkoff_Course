package edu.hw3;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task2 {
    private static final Logger LOGGER = LogManager.getLogger();

    private Task2() {

    }

    public static List<String> clusteringBrackets(String str) {
        LOGGER.info("String is: " + str);
        if (str == null) {
            throw new IllegalArgumentException("String is can not be null!");
        }
        int length = str.length();
        if (length == 0) {
            return new ArrayList<>();
        }
        if (length % 2 != 0) {
            throw new IllegalArgumentException("Invalid length of string!");
        }

        int balance = 0;
        List<String> result = new ArrayList<>();
        StringBuilder tempStr = new StringBuilder();

        for (int i = 0; i < length; i++) {
            if (str.charAt(i) != '(' && str.charAt(i) != ')') {
                throw new IllegalArgumentException("String extends invalid character!");
            }
            balance += str.charAt(i) == '(' ? 1 : -1;

            if (balance > length - i - 1) {
                throw new IllegalArgumentException("'(' more than ')'!");
            }

            tempStr.append(str.charAt(i));
            if (balance == 0) {
                result.add(tempStr.toString());
                tempStr.delete(0, tempStr.length() + 1);
            }
        }

        LOGGER.info("String after clustering brackets is: " + result);
        return result;
    }
}
