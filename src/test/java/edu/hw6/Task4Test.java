package edu.hw6;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Task4Test {
    private final static Logger LOGGER = LogManager.getLogger();
    public static String readFromFile(String fileName) {
        Path path = Paths.get(fileName);
        try {
            List<String> lines = Files.readAllLines(path);
            return String.join(" ", lines);
        } catch (IOException e) {
            LOGGER.info("Failed to start reading file. File not found");
            return null;
        }
    }

    @Test
    @DisplayName("Write to file")
    void writeToFile() {
        String file = "src/test/resources/hw6/task4.txt";
        String message = "Programming is learned by writing programs. ― Brian Kernighan";
        Task4.writeToFile(file, message);
        Assertions.assertEquals(readFromFile(file), message);
    }
}
