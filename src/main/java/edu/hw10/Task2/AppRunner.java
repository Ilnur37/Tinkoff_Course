package edu.hw10.Task2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

public class AppRunner {
    private AppRunner() {

    }

    @SuppressWarnings({"MagicNumber", "UncommentedMain"})
    public static void main(String[] args) {
        Path cacheDirectory = Paths.get("src/main/resources/Hw10Task2");

        if (!Files.exists(cacheDirectory)) {
            try {
                Files.createDirectory(cacheDirectory);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        FibCalculator fibCalculator = new FibCalculatorImpl();
        FibCalculator proxy = CacheProxy.create(fibCalculator, FibCalculator.class, cacheDirectory);

        proxy.fib(5);
        proxy.fib(6);
        proxy.fib(7);

        //deleteCacheDirectory(cacheDirectory);
    }

    private static void deleteCacheDirectory(Path directory) {
        try {
            Files.walk(directory)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
