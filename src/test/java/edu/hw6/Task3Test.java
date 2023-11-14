package edu.hw6;

import edu.hw6.Task3.AbstractFilter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task3Test {
    Path dir = Path.of("src/test/resources/hw6/task3");

    @Test
    @DisplayName("regularFile && readable")
    void filter1() throws IOException {
        DirectoryStream.Filter<Path> filter = AbstractFilter.and(
            AbstractFilter.regularFile(),
            AbstractFilter.readable()
        );

        List<Path> trueFiles = new ArrayList<>();
        trueFiles.add(Path.of(dir + "/abab.png"));
        trueFiles.add(Path.of(dir + "/abab.txt"));
        trueFiles.add(Path.of(dir + "/qwqw.png"));
        trueFiles.add(Path.of(dir + "/qwqw.txt"));

        List<Path> files = new ArrayList<>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            entries.forEach(files::add);
        }
        Assertions.assertEquals(trueFiles, files);
    }

    @Test
    @DisplayName("largerThan")
    void filter2() throws IOException {
        DirectoryStream.Filter<Path> filter = AbstractFilter.largerThan(1);

        List<Path> trueFiles = new ArrayList<>();
        trueFiles.add(Path.of(dir + "/abab.txt"));
        trueFiles.add(Path.of(dir + "/qwqw.txt"));

        List<Path> files = new ArrayList<>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            entries.forEach(files::add);
        }
        Assertions.assertEquals(trueFiles, files);
    }

    @Test
    @DisplayName("globMatches")
    void filter3() throws IOException {
        DirectoryStream.Filter<Path> filter = AbstractFilter.globMatches("*.png");

        List<Path> trueFiles = new ArrayList<>();
        trueFiles.add(Path.of(dir + "/abab.png"));
        trueFiles.add(Path.of(dir + "/qwqw.png"));

        List<Path> files = new ArrayList<>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            entries.forEach(files::add);
        }
        Assertions.assertEquals(trueFiles, files);
    }

    @Test
    @DisplayName("regexContains")
    void filter4() throws IOException {
        DirectoryStream.Filter<Path> filter = AbstractFilter.regexContains("qw");

        List<Path> trueFiles = new ArrayList<>();
        trueFiles.add(Path.of(dir + "/qwqw.png"));
        trueFiles.add(Path.of(dir + "/qwqw.txt"));

        List<Path> files = new ArrayList<>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            entries.forEach(files::add);
        }
        Assertions.assertEquals(trueFiles, files);
    }

    @Test
    @DisplayName("regularFile && readable && largerThan && regexContains")
    void filter5() throws IOException {
        DirectoryStream.Filter<Path> filter = AbstractFilter.and(
            AbstractFilter.regularFile(),
            AbstractFilter.readable(),
            AbstractFilter.largerThan(1),
            AbstractFilter.regexContains("qw")
        );

        List<Path> trueFiles = new ArrayList<>();
        trueFiles.add(Path.of(dir + "/qwqw.txt"));

        List<Path> files = new ArrayList<>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            entries.forEach(files::add);
        }
        Assertions.assertEquals(trueFiles, files);
    }
}
