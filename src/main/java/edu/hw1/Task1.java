package edu.hw1;

import java.math.BigInteger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task1 {
    private final static Logger LOGGER = LogManager.getLogger();
    private static final Long SECONDS_IN_MINUTE = 60L;
    private static final int SECONDS_MAX_VALUE = 59;

    private Task1() {

    }

    private static boolean stringIsValid(String str) {
        if (str == null) {
            throw new NullPointerException("String can not be null!");
        }
        String[] timeArr = str.split(":");
        if (str.length() == 0 || timeArr.length != 2) {
            return false;
        }
        for (String s : timeArr) {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) < '0' || s.charAt(i) > '9') {
                    return false;
                }
            }
        }

        BigInteger maxInt = BigInteger.valueOf(Integer.MAX_VALUE);
        BigInteger valueMin = new BigInteger(timeArr[0]);
        BigInteger valueSec = new BigInteger(timeArr[1]);
        if (valueMin.compareTo(maxInt) > 0
            || valueSec.compareTo(maxInt) > 0
            || Integer.parseInt(timeArr[1]) > SECONDS_MAX_VALUE) {
            return false;
        }

        return true;
    }

    public static long minutesToSeconds(String time) {
        LOGGER.info("Time is: " + time);
        if (!stringIsValid(time)) {
            return -1;
        }
        String[] timeArr = time.split(":");

        int minutes = Integer.parseInt(timeArr[0]);
        int seconds = Integer.parseInt(timeArr[1]);

        return seconds + minutes * SECONDS_IN_MINUTE;
    }

}
