package edu.hw7;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task1Test {

    @Test
    public void testCounter1() throws InterruptedException {
        final int threads = 1000;
        final int increments = 10000;
        Task1 task1 = new Task1();
        task1.counter(threads, increments);
        int res = task1.getCount();

        Assertions.assertEquals(threads * increments, res);
    }

    @Test
    public void testCounter_whenZeroThreads() throws InterruptedException {
        final int threads = 0;
        final int increments = 50000;
        Task1 task1 = new Task1();
        task1.counter(threads, increments);
        int res = task1.getCount();

        Assertions.assertEquals(threads * increments, res);
    }

    @Test
    public void testCounter_whenNegativeThreads() {
        final int threads = -1;
        final int increments = 10000;
        Task1 task1 = new Task1();
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            task1.counter(threads, increments);
        }, "Arguments is valid");
        Assertions.assertEquals("Arguments can not be negative!", thrown.getMessage());
    }

    @Test
    public void testCounter_whenNegativeIncrements() {
        final int threads = 1000;
        final int increments = -1;
        Task1 task1 = new Task1();
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            task1.counter(threads, increments);
        }, "Arguments is valid");
        Assertions.assertEquals("Arguments can not be negative!", thrown.getMessage());
    }
}
