package edu.hw10.Task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RecordTest {
    @Test
    void oneIntConsrt() {
        RandomObjectGenerator rog = new RandomObjectGenerator();
        for (int i = 0; i < 1000; i++) {
            var myRecord = rog.nextObject(MyRecord.class);
            Assertions.assertAll(
                () -> Assertions.assertTrue(myRecord.num() < 3 && myRecord.num() >= 1),
                () -> Assertions.assertNotNull(myRecord.string())
            );
        }
    }
}
