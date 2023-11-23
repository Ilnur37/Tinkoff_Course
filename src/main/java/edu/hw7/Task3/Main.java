package edu.hw7.Task3;

public class Main {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        for (int z = 0; z < 10000; z++) {
            PersonDB personDB = new PersonDB();
            Thread t1 = new Thread(() -> {
                for (int i = 0; i < 10000; i++) {
                    personDB.add(new Person(i, "a" + i, "a" + i, "a" + i));
                    //System.out.println("iiii = " + i);
                }
            });
            Thread t3 = new Thread(() -> {
                for (int i = 0; i < 10000; i += 2) {
                    int c = 0;
                    synchronized (personDB) {
                        if (personDB.findByName("a" + i).size() == 0) {
                            c++;
                        }
                        if (personDB.findByAddress("a" + i).size() == 0) {
                            c++;
                        }
                        if (personDB.findByPhone("a" + i).size() == 0) {
                            c++;
                        }
                        if (c == 1 || c == 2) {
                            System.out.println("i = " + i + " error");
                        }
                    }
                }
            });
            t1.start();
            t3.start();
            try {
                t1.join();
                t3.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            //System.out.println(duration / 1000000);
            //System.out.println(personDB);
        }
    }
}
