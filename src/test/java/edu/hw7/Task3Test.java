package edu.hw7;

import edu.hw7.Task3.Person;
import edu.hw7.Task3.PersonDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.concurrent.atomic.AtomicInteger;

public class Task3Test {
    @Test
    public void task3() {
        for (int z = 0; z < 30000; z++) {
            PersonDB personDB = new PersonDB();
            AtomicInteger countOfEx = new AtomicInteger();
            Thread t1 = new Thread(() -> {
                for (int i = 0; i < 1000; i++) {
                    personDB.add(new Person(i, "a" + i, "a" + i, "a" + i));
                }
            });
            Thread t2 = new Thread(() -> {
                for (int i = 0; i < 1000; i += 2) {
                    int tempCount = 0;
                    synchronized (personDB) {
                        if (personDB.findByName("a" + i).size() == 0) {
                            ++tempCount;
                        }
                        if (personDB.findByAddress("a" + i).size() == 0) {
                            ++tempCount;
                        }
                        if (personDB.findByPhone("a" + i).size() == 0) {
                            ++tempCount;
                        }
                        if (tempCount == 1 || tempCount == 2) {
                            countOfEx.incrementAndGet();
                        }
                    }
                }
            });
            t1.start();
            t2.start();
            try {
                t1.join();
                t2.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            Assertions.assertEquals(0, countOfEx.get());
        }
    }
}
