package edu.hw6;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Task2 {
    public static void cloneFile(Path path) throws IOException {
        if (!Files.exists(path)) {
            throw new IllegalArgumentException("Файл не существует: " + path);
        }

        String fileName = path.getFileName().toString();
        int idxOfDot = fileName.lastIndexOf('.');
        String baseName = idxOfDot != -1 ? fileName.substring(0, fileName.lastIndexOf('.')) : fileName;
        String extension = idxOfDot != -1 ? fileName.substring(fileName.lastIndexOf('.')) : "";
        int counter = 1;

        while (true) {
            String newFileName = baseName + " — копия" + (counter > 1 ? " (" + counter + ")" : "") + extension;
            Path newPath = path.resolveSibling(newFileName);

            try {
                Files.copy(path, newPath);
                break;
            } catch (FileAlreadyExistsException e) {
                counter++;
            }
        }
    }
}
