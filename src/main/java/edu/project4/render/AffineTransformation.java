package edu.project4.render;

import edu.project4.Pixel;
import java.util.concurrent.ThreadLocalRandom;

public class AffineTransformation {
    public final double[] affine;
    public final int red;
    public final int green;
    public final int blue;
    private static final double MIN_DEFAULT = -1.5;
    private static final double MAX_DEFAULT = 1.5;

    public AffineTransformation() {
        this.affine = createTransforms();
        this.red = Pixel.randomColor();
        this.green = Pixel.randomColor();
        this.blue = Pixel.randomColor();
    }

    public double[] createTransforms() {
        double a;
        double b;
        double c;
        double d;
        double e;
        double f;
        if (ThreadLocalRandom.current().nextBoolean()) {
            do {
                do {
                    a = ThreadLocalRandom.current().nextDouble(-1, 1);
                    d = ThreadLocalRandom.current().nextDouble(a * a * (a / Math.abs(a)), 1);
                    if (ThreadLocalRandom.current().nextBoolean()) {
                        d = -d;
                    }
                }
                while ((a * a + d * d) > 1);
                do {
                    b = ThreadLocalRandom.current().nextDouble(-1, 1);
                    e = ThreadLocalRandom.current().nextDouble(b * b * (b / Math.abs(b)), 1);
                    if (ThreadLocalRandom.current().nextBoolean()) {
                        e = -e;
                    }
                }
                while ((b * b + e * e) > 1);
            }
            while ((a * a + b * b + d * d + e * e) > (1 + (a * e - d * b) * (a * e - d * b)));
            c = ThreadLocalRandom.current().nextDouble(-1, 1);
            f = ThreadLocalRandom.current().nextDouble(-1, 1);
        } else {
            a = ThreadLocalRandom.current().nextDouble(MIN_DEFAULT, MAX_DEFAULT);
            b = ThreadLocalRandom.current().nextDouble(MIN_DEFAULT, MAX_DEFAULT);
            d = ThreadLocalRandom.current().nextDouble(MIN_DEFAULT, MAX_DEFAULT);
            e = ThreadLocalRandom.current().nextDouble(MIN_DEFAULT, MAX_DEFAULT);
            c = ThreadLocalRandom.current().nextDouble(MIN_DEFAULT, MAX_DEFAULT);
            f = ThreadLocalRandom.current().nextDouble(MIN_DEFAULT, MAX_DEFAULT);
        }

        return new double[] {
            a, b, c,
            d, e, f
        };
    }
}
