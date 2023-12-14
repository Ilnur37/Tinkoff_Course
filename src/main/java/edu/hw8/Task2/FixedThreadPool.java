package edu.hw8.Task2;

import java.util.concurrent.LinkedBlockingQueue;

public class FixedThreadPool implements ThreadPool {
    private final int poolSize;
    private final Thread[] threads;
    private final LinkedBlockingQueue<Runnable> taskQueue;

    private FixedThreadPool(int poolSize) {
        this.poolSize = poolSize;
        this.threads = new Thread[poolSize];
        this.taskQueue = new LinkedBlockingQueue<>();
    }

    public static FixedThreadPool create(int poolSize) {
        return new FixedThreadPool(poolSize);
    }

    @Override
    public void start() {
        for (int i = 0; i < this.poolSize; ++i) {
            this.threads[i] = new Thread(new Worker());
            this.threads[i].start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        try {
            this.taskQueue.put(runnable);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void close() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    private class Worker implements Runnable {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Runnable task = FixedThreadPool.this.taskQueue.take();
                    task.run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
