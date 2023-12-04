package edu.project4;

import java.util.concurrent.ThreadLocalRandom;

public record Pixel(int r, int g, int b, double hitCount) {
    public static int randomColor() {
        return 64 + ThreadLocalRandom.current().nextInt(64, 256);
    }
}
