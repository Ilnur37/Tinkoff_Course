package edu.hw7;

import java.util.concurrent.atomic.AtomicInteger;

public class Task1 {
    private AtomicInteger count = new AtomicInteger(0);

    public int getCount() {
        return count.get();
    }

    public void counter(int numThreads, int numIncrements) throws InterruptedException {
        if (numIncrements < 0 || numThreads < 0) {
            throw new IllegalArgumentException("Arguments can not be negative!");
        }
        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < numIncrements; j++) {
                    count.incrementAndGet();
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
    }
}
