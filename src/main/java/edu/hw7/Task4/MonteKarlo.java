package edu.hw7.Task4;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MonteKarlo {
    public static final int MULTIPLY = 4;

    public static double calculatePi(int n) {
        int circlePoints = 0;

        for (int i = 0; i < n; i++) {
            double randX = ThreadLocalRandom.current().nextDouble();
            double randY = ThreadLocalRandom.current().nextDouble();

            if ((randX * randX) + (randY * randY) <= 1) {
                circlePoints++;
            }
        }
        return MULTIPLY * ((double) circlePoints / n);
    }

    public static double calculatePi(int n, int numThreads) {
        if (numThreads < 1) {
            throw new IllegalArgumentException("The number of threads cannot be less than 1");
        }
        AtomicInteger circlePoints = new AtomicInteger();

        Thread[] threads = new Thread[numThreads];
        int generationCount = n / numThreads;

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                int tempCirclePoints = 0;
                for (int j = 0; j < generationCount; j++) {
                    double randX = ThreadLocalRandom.current().nextDouble();
                    double randY = ThreadLocalRandom.current().nextDouble();

                    if ((randX * randX) + (randY * randY) <= 1) {
                        tempCirclePoints++;
                    }
                }
                circlePoints.addAndGet(tempCirclePoints);
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return MULTIPLY * ((double) circlePoints.get() / n);
    }
}
