package edu.hw7;

import edu.hw7.Task3And3_5.Person;
import edu.hw7.Task3And3_5.Task3.PersonDBSynchronized;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task3_5Test {
    @Test
    public void personDB() {
        for (int z = 0; z < 3000; z++) {
            PersonDBSynchronized personDB = new PersonDBSynchronized();
            AtomicInteger countOfEx = new AtomicInteger();
            Thread t1 = new Thread(() -> {
                for (int i = 0; i < 100; i++) {
                    personDB.add(new Person(i, "a" + i, "a" + i, "a" + i));
                }
            });

            Thread[] threads = new Thread[15];
            for (int i = 0; i < threads.length; i++) {
                threads[i] = new Thread(() -> {
                    for (int j = 0; j < 100; j += 2) {
                        int tempCount = 0;
                        synchronized (personDB) {
                            if (personDB.findByName("a" + j).size() == 0) {
                                ++tempCount;
                            }
                            if (personDB.findByAddress("a" + j).size() == 0) {
                                ++tempCount;
                            }
                            if (personDB.findByPhone("a" + j).size() == 0) {
                                ++tempCount;
                            }
                            if (tempCount == 1 || tempCount == 2) {
                                countOfEx.incrementAndGet();
                            }
                        }
                    }
                });
            }

            t1.start();
            for (Thread thread : threads) {
                thread.start();
            }
            try {
                t1.join();
                for (Thread thread : threads) {
                    thread.join();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            Assertions.assertEquals(0, countOfEx.get());
        }
    }
}
