package edu.project4.render;

import edu.project4.Pixel;
import edu.project4.Point;
import edu.project4.image.FractalImage;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;

public interface Render {
    int START_STEP = -20;
    double X_MAX = 1.77;
    double X_MIN = -1.77;
    double Y_MAX = 1;
    double Y_MIN = -1;

    FractalImage render(
        FractalImage canvas,
        List<AffineTransformation> transforms,
        List<String> namesOfTrans,
        short numberOfTrans,
        int samples,
        short iterPerSample,
        int symmetry
    ) throws ExecutionException, InterruptedException;

    default Point randomPoint() {
        double x = X_MIN + ((X_MAX - X_MIN) * ThreadLocalRandom.current().nextDouble());
        double y = Y_MIN + ((Y_MAX - Y_MIN) * ThreadLocalRandom.current().nextDouble());
        return new Point(x, y);
    }

    default Point rotate(Point newPoint, double theta2) {
        double x = ((newPoint.x() * Math.cos(theta2) - newPoint.y() * Math.sin(theta2)));
        double y = ((newPoint.x() * Math.sin(theta2) + newPoint.y() * Math.cos(theta2)));
        return new Point(x, y);
    }

    @SuppressWarnings("MagicNumber")
    default Point affineTransformation(AffineTransformation affineTrans, Point point) {
        double affineX = affineTrans.affine[0] * point.x() + affineTrans.affine[1] * point.y()
            + affineTrans.affine[2];
        double affineY = affineTrans.affine[3] * point.x() + affineTrans.affine[4] * point.y()
            + affineTrans.affine[5];
        return new Point(affineX, affineY);
    }

    default void gammaCorrection(FractalImage canvas) {
        double max = 0.0;
        double gamma = 8;
        for (int col = 0; col < canvas.width(); col++) {
            for (int row = 0; row < canvas.height(); row++) {
                Pixel pixel = canvas.getPixel(col, row);
                if (pixel == null) {
                    continue;
                }
                max = Math.max(max, pixel.hitCount());
            }
        }
        max = Math.log10(max);
        for (int col = 0; col < canvas.width(); col++) {
            for (int row = 0; row < canvas.height(); row++) {
                Pixel pixel = canvas.getPixel(col, row);
                if (pixel == null) {
                    continue;
                }
                double newHitC = Math.log10(pixel.hitCount());
                newHitC = newHitC / max;
                double multi = Math.pow(newHitC, (1 / gamma));
                int newRed = (int) (pixel.r() * multi);
                int newGreen = (int) (pixel.g() * multi);
                int newBlue = (int) (pixel.b() * multi);

                canvas.addPixel(col, row, new Pixel(newRed, newGreen, newBlue, newHitC));
            }
        }
    }
}
