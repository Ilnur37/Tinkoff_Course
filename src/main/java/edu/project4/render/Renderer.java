package edu.project4.render;

import edu.project4.Point;
import edu.project4.image.FractalImage;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;

public interface Renderer {
    double xMax = 1.77;
    double xMin = -1.77;
    double yMax = 1;
    double yMin = -1;

    FractalImage render(
        FractalImage canvas,
        List<AffineTransformation> transforms,
        List<String> namesOfTrans,
        short numberOfTrans,
        int samples,
        short iterPerSample,
        int symmetry
    ) throws ExecutionException, InterruptedException;

    void gammaCorrection(FractalImage canvas);

    default Point randomPoint() {
        double x = -1.77 + ((1.77 - (-1.77)) * ThreadLocalRandom.current().nextDouble());
        double y = -1 + ((1 - (-1)) * ThreadLocalRandom.current().nextDouble());
        return new Point(x, y);
    }

    default Point rotate(Point newPoint, double theta2) {
        double x = ((newPoint.x() * Math.cos(theta2) - newPoint.y() * Math.sin(theta2)));
        double y = ((newPoint.x() * Math.sin(theta2) + newPoint.y() * Math.cos(theta2)));
        return new Point(x, y);
    }

    default Point affineTransformation(AffineTransformation affineTrans, Point point) {
        double affineX = affineTrans.affine[0] * point.x() + affineTrans.affine[1] * point.y() +
            affineTrans.affine[2];
        double affineY = affineTrans.affine[3] * point.x() + affineTrans.affine[4] * point.y() +
            affineTrans.affine[5];
        return new Point(affineX, affineY);
    }
}
