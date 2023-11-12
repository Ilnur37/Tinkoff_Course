package edu.hw6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Task2 {
    private Task2() {

    }

    public static void cloneFile(Path path) throws IOException {
        if (!Files.exists(path)) {
            throw new IllegalArgumentException("Файл не существует: " + path);
        }

        Path parent = path.getParent();
        String fileName = path.getFileName().toString();
        String baseName;
        String extension;

        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0) {
            baseName = fileName.substring(0, dotIndex);
            extension = fileName.substring(dotIndex);
        } else {
            baseName = fileName;
            extension = "";
        }

        int counter = 1;
        Path newPath;
        do {
            String newFileName = baseName + " — копия" + (counter > 1 ? " (" + counter + ")" : "") + extension;
            newPath = parent.resolve(newFileName);
            counter++;
        } while (Files.exists(newPath));

        Files.copy(path, newPath);
    }
}
