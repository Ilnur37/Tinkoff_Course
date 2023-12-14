package edu.hw8.Task2;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import lombok.experimental.UtilityClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@UtilityClass
public final class Fibonacci {
    private static final Logger LOGGER = LogManager.getLogger();

    @SuppressWarnings("MagicNumber")
    public static List<Integer> calculate(int countThread, int fibonacci) {
        List<Integer> list = new CopyOnWriteArrayList<>();
        try (FixedThreadPool threadPool = FixedThreadPool.create(countThread)) {
            threadPool.start();

            for (int i = 0; i < fibonacci; i++) {
                int taskId = i;
                threadPool.execute(() -> {
                    int fib = calculateFibonacci(taskId);
                    LOGGER.info("Task " + taskId + ": Fibonacci(" + taskId + ") = " + fib);
                    list.add(fib);
                });
            }
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    private static int calculateFibonacci(int n) {
        return n <= 1 ? n : calculateFibonacci(n - 1) + calculateFibonacci(n - 2);
    }
}

