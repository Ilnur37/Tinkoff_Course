package edu.hw6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task2Test {
    @Test
    @DisplayName("get & containsKey")
    void cloneFile() throws IOException {
        Path path1 = Path.of("src/test/resources/hw6/task2/File For Test.txt");
        Path path2 = Path.of("src/test/resources/hw6/task2");
        Task2.cloneFile(path1);
        Task2.cloneFile(path1);
        Task2.cloneFile(path1);
        Task2.cloneFile(path2);
        Task2.cloneFile(path2);

        Path pathCopy = Path.of("src/test/resources/hw6/task2/File For Test — копия.txt");
        Path pathCopy2 = Path.of("src/test/resources/hw6/task2/File For Test — копия (2).txt");
        Path pathCopy3 = Path.of("src/test/resources/hw6/task2/File For Test — копия (3).txt");
        Path pathCopyDir = Path.of("src/test/resources/hw6/task2 — копия");
        Path pathCopyDir2 = Path.of("src/test/resources/hw6/task2 — копия (2)");

        Assertions.assertAll(
            () -> Assertions.assertTrue(Files.exists(pathCopy)),
            () -> Assertions.assertTrue(Files.exists(pathCopy2)),
            () -> Assertions.assertTrue(Files.exists(pathCopy3)),
            () -> Assertions.assertTrue(Files.exists(pathCopyDir)),
            () -> Assertions.assertTrue(Files.exists(pathCopyDir2))
        );
    }
}
