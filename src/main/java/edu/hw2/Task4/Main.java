package edu.hw2.Task4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    private Main() {

    }

    public static void main(String[] args) {
        CallingInfo callingInfo = func();
        LOGGER.info(callingInfo);
    }

    private static CallingInfo func() {
        return CallingInfo.callingInfo();
    }
}
