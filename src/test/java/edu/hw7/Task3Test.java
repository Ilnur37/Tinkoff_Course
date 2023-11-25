package edu.hw7;

import edu.hw7.Task3And3_5.Person;
import edu.hw7.Task3And3_5.Task3.PersonDBSynchronized;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.concurrent.atomic.AtomicInteger;

public class Task3Test {
    @Test
    public void personDB() {
        for (int z = 0; z < 10000; z++) {
            PersonDBSynchronized personDB = new PersonDBSynchronized();
            AtomicInteger countOfEx = new AtomicInteger();
            Thread t1 = new Thread(() -> {
                for (int i = 0; i < 100; i++) {
                    personDB.add(new Person(i, "a" + i, "a" + i, "a" + i));
                }
            });
            Thread t2 = new Thread(() -> {
                for (int i = 0; i < 100; i += 2) {
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
            Thread t3 = new Thread(() -> {
                for (int i = 0; i < 100; i += 2) {
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
            t3.start();
            try {
                t1.join();
                t2.join();
                t3.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            Assertions.assertEquals(0, countOfEx.get());
        }
    }

    @Test
    public void personDB_whenNullParameter() {
        PersonDBSynchronized personDB = new PersonDBSynchronized();
        String exMsg = "Parameters of person can not be null!";
        IllegalArgumentException thrown1 = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            personDB.add(new Person(1, null, "a", "a"));
        }, "Person is valid");
        IllegalArgumentException thrown2 = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            personDB.add(new Person(1, "a", null, "a"));
        }, "Person is valid");
        IllegalArgumentException thrown3 = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            personDB.add(new Person(1, "a", "a", null));
        }, "Person is valid");
        Assertions.assertAll(
            () -> Assertions.assertEquals(exMsg, thrown1.getMessage()),
            () -> Assertions.assertEquals(exMsg, thrown2.getMessage()),
            () -> Assertions.assertEquals(exMsg, thrown3.getMessage())
        );
    }

    @Test
    public void personDB_whenRepeatID() {
        PersonDBSynchronized personDB = new PersonDBSynchronized();
        personDB.add(new Person(1, "a", "a", "a"));
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            personDB.add(new Person(1, "a", "a", "a"));
        }, "Person is valid");
        Assertions.assertEquals("Person with this ID already exists!", thrown.getMessage());

    }
}
