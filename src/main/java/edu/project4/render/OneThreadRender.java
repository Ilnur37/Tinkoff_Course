package edu.project4.render;

import edu.project4.Point;
import edu.project4.Rect;
import java.util.concurrent.ThreadLocalRandom;

public class OneThreadRender implements Renderer {
    @Override
    public Point randomPoint(Rect world) {
        //((lo) + (((hi)-(lo)) * drand48()))
        //double x = ThreadLocalRandom.current().nextDouble(0, 3.44);
        double x =  -1.77 + ((1.77 - (-1.77)) * ThreadLocalRandom.current().nextDouble());
        //double y = ThreadLocalRandom.current().nextDouble(0, 3.44);
        double y = -1 + ((1 - (-1)) * ThreadLocalRandom.current().nextDouble());
        return new Point(x, y);
    }

    @Override
    public double[] createTransforms() {
        double a, b, d, e;
        do {
            do {
                a = ThreadLocalRandom.current().nextDouble();
                d = ThreadLocalRandom.current().nextDouble(a * a, 1);
                if (ThreadLocalRandom.current().nextInt() == 0) {
                    d = -d;
                }
            }
            while ((a * a + d * d) > 1);
            do {
                b = ThreadLocalRandom.current().nextDouble();
                e = ThreadLocalRandom.current().nextDouble(b * b, 1);
                if (ThreadLocalRandom.current().nextInt() == 0) {
                    e = -e;
                }
            }
            while ((b * b + e * e) > 1);
        }
        while ((a * a + b * b + d * d + e * e) >
            (1 + (a * e - d * b) * (a * e - d * b)));

        return new double[] {
            a, b, ThreadLocalRandom.current().nextDouble(-2, 2),
            d, e, ThreadLocalRandom.current().nextDouble(-2, 2)
        };
    }

    @Override
    public Point rotate(Point newPoint, double theta2) {
        double x = ((newPoint.x() * Math.cos(theta2) - newPoint.y() * Math.sin(theta2)));
        double y = ((newPoint.x() * Math.sin(theta2) + newPoint.y() * Math.cos(theta2)));
        return new Point(x, y);
    }
}
