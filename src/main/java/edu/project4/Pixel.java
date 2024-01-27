package edu.project4;

import java.util.concurrent.ThreadLocalRandom;

public record Pixel(double r, double g, double b, double hitCount) {

    @SuppressWarnings("MagicNumber")
    public static int randomColor() {
        return ThreadLocalRandom.current().nextInt(0, 256);
    }
}
