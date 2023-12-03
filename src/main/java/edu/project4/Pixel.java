package edu.project4;

import java.util.concurrent.ThreadLocalRandom;

public record Pixel(int r, int g, int b, int hitCount) {
    public static int randomColor() {
        return ThreadLocalRandom.current().nextInt(0, 256);
    }
}
