package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task4 {
    private final static Logger LOGGER = LogManager.getLogger();

    private Task4() {

    }

    public static String fixString(String str) {
        LOGGER.info("Broken line: " + str);
        if (str == null) {
            throw new NullPointerException("String can not be null!");
        }
        if (str.length() < 2) {
            return str;
        }
        StringBuilder resultString = new StringBuilder();
        for (int i = 0; i < str.length(); i += 2) {
            if (i + 1 < str.length()) {
                resultString.append(str.charAt(i + 1));
            }
            resultString.append(str.charAt(i));
        }
        return resultString.toString();
    }

}
