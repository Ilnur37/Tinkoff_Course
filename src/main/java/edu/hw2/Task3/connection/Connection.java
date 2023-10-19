package edu.hw2.Task3.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface Connection extends AutoCloseable {
    Logger LOGGER = LogManager.getLogger();
    default void execute(String command) {
        LOGGER.info(getClass() + " execute command: " + command);
    }

    @Override
    default void close() throws Exception {
        LOGGER.info(getClass() + " connection close.");
    }
}
