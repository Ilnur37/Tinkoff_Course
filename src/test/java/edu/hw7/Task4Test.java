package edu.hw7;

import edu.hw7.Task4.MonteKarlo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task4Test {
    @Test
    public void calculatePi() {
        double pi = MonteKarlo.calculatePi(10000000);
        double eps = 0.001;

        Assertions.assertTrue((Math.PI - eps < pi) && (Math.PI + eps > pi));
    }

    @Test
    public void calculatePi_whenNegativeOrZeroThread() {
        String res = "The number of threads cannot be less than 1";
        IllegalArgumentException thrown1 = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            MonteKarlo.calculatePi(100000, 0);
        }, "Arguments are valid");
        IllegalArgumentException thrown2 = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            MonteKarlo.calculatePi(100000, -10);
        }, "Arguments are valid");
        Assertions.assertAll(
            () -> Assertions.assertEquals(res, thrown1.getMessage()),
            () -> Assertions.assertEquals(res, thrown2.getMessage())
        );
    }
}
