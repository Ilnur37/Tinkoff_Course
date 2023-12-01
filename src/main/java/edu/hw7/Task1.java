package edu.hw7;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Task1 {
    private final AtomicInteger count = new AtomicInteger(0);

    public int getCount() {
        return count.get();
    }

    public void counter(int numThreads, int numIncrements) throws ExecutionException, InterruptedException {
        if (numIncrements < 0 || numThreads <= 0) {
            throw new IllegalArgumentException("Arguments can not be negative!");
        }
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        Callable<Void> callable = () -> {
            for (int j = 0; j < numIncrements; j++) {
                count.incrementAndGet();
            }
            return null;
        };
        var tasks = Stream.generate(() -> callable).limit(numThreads).toList();
        List<Future<Void>> futures = executorService.invokeAll(tasks);
        for (var future : futures) {
            future.get();
        }
        executorService.shutdown();
    }
}
