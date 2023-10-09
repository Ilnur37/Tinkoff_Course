package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task1 {
    private final static Logger LOGGER = LogManager.getLogger();
    private final static int MAXIMUM_VIDEO_LENGTH_IN_SECONDS = 172800;
    private final static int INCREASE_THE_DIGIT_OF_NUMBER = 10;
    private final static int SECONDS_MAX_VALUE = 60;
    private final static int MINUTES_MAX_VALUE = Integer.MAX_VALUE / SECONDS_MAX_VALUE;

    private Task1() {

    }

    private static boolean stringIsValid(String str) {
        if (str == null) {
            return false;
        }
        String[] timeArr = str.split(":");
        if (str.length() == 0
            || timeArr.length != 2
            || timeArr[0].length() < 2
            || timeArr[1].length() != 2) {
            return false;
        }
        for (String s : timeArr) {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) < '0' || s.charAt(i) > '9') {
                    return false;
                }
            }
        }

        return true;
    }

    public static int minutesToSeconds(String time) {
        LOGGER.info("Time is: " + time);
        if (!stringIsValid(time)) {
            return -1;
        }
        int seconds = 0;
        String[] timeArr = time.split(":");

        for (int i = 0; i < timeArr[1].length(); i++) {
            seconds = seconds * INCREASE_THE_DIGIT_OF_NUMBER;
            seconds += Character.getNumericValue(timeArr[1].charAt(i));
            if (seconds >= SECONDS_MAX_VALUE) {
                return -1;
            }
        }

        int digitOfNumber = 1;
        for (int i = timeArr[0].length() - 1; i > -1; i--) {
            int minute = Character.getNumericValue(timeArr[0].charAt(i)) * digitOfNumber;
            digitOfNumber *= INCREASE_THE_DIGIT_OF_NUMBER;
            seconds += minute * SECONDS_MAX_VALUE;
            if (seconds > MAXIMUM_VIDEO_LENGTH_IN_SECONDS) {
                return -1;
            }
        }

        return seconds;
    }

}
