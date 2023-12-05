package edu.project4.render;

import edu.project4.Pixel;
import java.util.concurrent.ThreadLocalRandom;

public class AffineTransformation {
    public final double[] affine;
    public final int red;
    public final int green;
    public final int blue;

    public AffineTransformation() {
        this.affine = createTransforms();
        this.red = Pixel.randomColor();
        this.green = Pixel.randomColor();
        this.blue = Pixel.randomColor();
    }

    public double[] createTransforms() {
        double a, b, d, e;
        do {
            do {
                a = ThreadLocalRandom.current().nextDouble(-1, 1);
                d = ThreadLocalRandom.current().nextDouble(a * a * (a / Math.abs(a)), 1);
                /*a = ThreadLocalRandom.current().nextDouble();
                d = ThreadLocalRandom.current().nextDouble(a * a, 1);*/
                if (ThreadLocalRandom.current().nextInt() == 0) {
                    d = -d;
                }
            }
            while ((a * a + d * d) > 1);
            do {
                b = ThreadLocalRandom.current().nextDouble(-1,1);
                e = ThreadLocalRandom.current().nextDouble(b * b * (b / Math.abs(b)), 1);
               /* b = ThreadLocalRandom.current().nextDouble();
                e = ThreadLocalRandom.current().nextDouble(b * b, 1);*/
                if (ThreadLocalRandom.current().nextInt() == 0) {
                    e = -e;
                }
            }
            while ((b * b + e * e) > 1);
        }
        while ((a * a + b * b + d * d + e * e) > (1 + (a * e - d * b) * (a * e - d * b)));

        return new double[] {
            a, b, ThreadLocalRandom.current().nextDouble(-2, 2),
            d, e, ThreadLocalRandom.current().nextDouble(-2, 2)
            /*Math.random(), Math.random(), Math.random(),
            Math.random(), Math.random(), Math.random()*/
            /*ThreadLocalRandom.current().nextDouble(-1, 1),
            ThreadLocalRandom.current().nextDouble(-1, 1),
            ThreadLocalRandom.current().nextDouble(-1, 1),
            ThreadLocalRandom.current().nextDouble(-1, 1),
            ThreadLocalRandom.current().nextDouble(),
            ThreadLocalRandom.current().nextDouble(),*/
        };
    }
}
