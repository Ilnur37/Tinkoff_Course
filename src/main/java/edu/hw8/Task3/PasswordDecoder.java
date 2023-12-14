package edu.hw8.Task3;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public interface PasswordDecoder {
    int DICTIONARY_POWER = 62;
    Logger LOGGER = LogManager.getLogger();
    Map<String, String> PASSWORD_BY_USER = new HashMap<>();

    void encodePasswordArray(@NotNull Map<String, String> users);

    void decodePassword(int maxLength) throws InterruptedException, ExecutionException;

    @SuppressWarnings("MagicNumber")
    default char getChar(int index) {
        if (index < 10) {
            return (char) ('0' + index);
        } else if (index < 36) {
            return (char) ('a' + index - 10);
        } else {
            return (char) ('A' + index - 36);
        }
    }
}
