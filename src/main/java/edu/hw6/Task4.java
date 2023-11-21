package edu.hw6;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import lombok.experimental.UtilityClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@UtilityClass
public class Task4 {
    private final static Logger LOGGER = LogManager.getLogger();
    private final static String FILE_WRITER_EXCEPTION = "Failed to start reading file. File not found";

    public static void writeToFile(String fileName, String text) {
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(fileName));
             CheckedOutputStream checkedOutputStream = new CheckedOutputStream(outputStream, new CRC32());
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(checkedOutputStream);
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                 bufferedOutputStream,
                 StandardCharsets.UTF_8
             );
             PrintWriter printWriter = new PrintWriter(outputStreamWriter)) {

            printWriter.println(text);

        } catch (IOException e) {
            LOGGER.info(FILE_WRITER_EXCEPTION);
        }
    }
}
