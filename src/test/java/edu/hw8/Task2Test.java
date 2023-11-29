
package edu.hw8;

import edu.hw8.Task2.Fibonacci;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task2Test {
    @Test
    public void fibonacci() {
        List<Integer> fibonacci = new ArrayList<>(List.of(0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377));
        List<Integer> resOfThreadPool = Fibonacci.calculate(3, 15);
        Assertions.assertTrue(
            fibonacci.size() == resOfThreadPool.size()
                && fibonacci.containsAll(resOfThreadPool)
                && resOfThreadPool.containsAll(fibonacci));
    }
}
