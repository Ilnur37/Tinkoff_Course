package edu.hw7.Task4;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class MonteKarlo {
    public static double calculatePi(int n) {
        long start = System.nanoTime();
        int circlePoints = 0;

        for (int i = 0; i < n; i++) {
            double randX = ThreadLocalRandom.current().nextDouble();
            double randY = ThreadLocalRandom.current().nextDouble();

            if ((randX * randX) + (randY * randY) <= 1) {
                circlePoints++;
            }
        }
        System.out.println((System.nanoTime() - start) / 1000000);
        return 4.0 * ((double) circlePoints / n);
    }

    public static double calculatePi(int n, int numThreads) {
        long start = System.nanoTime();
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
        System.out.println((System.nanoTime() - start) / 1000000);
        return 4.0 * ((double) circlePoints.get() / n);
    }

    public static void main(String[] args) {
        int n =  100000000;
        System.out.println(calculatePi(n));
        System.out.println(calculatePi(n, 4));
    }
}
