package edu.project4.render;

import edu.project4.Pixel;
import edu.project4.Point;
import edu.project4.image.FractalImage;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class OneThreadRender implements Renderer {

    @Override
    public FractalImage render(
        FractalImage canvas,
        List<AffineTransformation> transforms,
        List<String> namesOfTrans,
        short numberOfTrans,
        int samples,
        short iterPerSample
    ) {
        int xImage = canvas.width();
        int yImage = canvas.height();
        double xMax = 1.77;
        double xMin = -1.77;
        double yMax = 1;
        double yMin = -1;

        List<NonlinearTransformation> nonlinearTrans = Transformation.getTransformations(namesOfTrans, numberOfTrans);

        for (int num = 0; num < samples; ++num) {
            Point firstPoint = randomPoint();

            for (short step = 0; step < iterPerSample; ++step) {
                int randomTrans = ThreadLocalRandom.current().nextInt(transforms.size());
                AffineTransformation affineTrans = transforms.get(randomTrans);
                double affineX = affineTrans.affine[0] * firstPoint.x() + affineTrans.affine[1] * firstPoint.y() +
                    affineTrans.affine[2];
                double affineY = affineTrans.affine[3] * firstPoint.x() + affineTrans.affine[4] * firstPoint.y() +
                    affineTrans.affine[5];
                Point affinePoint = new Point(affineX, affineY);

                int randomTrans2 = ThreadLocalRandom.current().nextInt(nonlinearTrans.size());
                Point newPoint = nonlinearTrans.get(randomTrans2).apply(affinePoint);
                //Point newPoint = affinePoint;
                double theta2 = 0.0;
                int symmetry = 1;
                for (int s = 0; s < symmetry; theta2 += Math.PI * 2 / symmetry, ++s) {
                    Point pwr = rotate(newPoint, theta2);
                    if (!(pwr.x() >= xMin && pwr.x() <= xMax
                        && pwr.y() >= yMin && pwr.y() <= yMax)) {
                        continue;
                    }
                    int x = (xImage - (int) (((xMax - pwr.x()) / (xMax - xMin)) * xImage));
                    int y = (yImage - (int) (((yMax - pwr.y()) / (yMax - yMin)) * yImage));

                    if (!(x >= 0 && x < xImage
                        && y >= 0 && y < yImage)) {
                        continue;
                    }
                    Pixel pixel = canvas.getPixel(x, y);
                    if (pixel == null) {
                        pixel = new Pixel(affineTrans.red, affineTrans.green, affineTrans.blue, 1);
                    } else {
                        pixel = new Pixel(
                            (pixel.r() + affineTrans.red) / 2,
                            (pixel.r() + affineTrans.green) / 2,
                            (pixel.r() + affineTrans.blue) / 2,
                            pixel.hitCount() + 1
                        );
                    }
                    canvas.addPixel(x, y, pixel);
                }
            }
        }
        return canvas;
    }

    @Override
    public void gammaCorrection(FractalImage canvas) {
        double max = 0;
        double gamma = 2.2;
        for (int col = 0; col < canvas.width(); col++) {
            for (int row = 0; row < canvas.height(); row++) {
                Pixel pixel = canvas.getPixel(col, row);
                if (pixel == null) {
                    continue;
                }
                max = Math.max(max, pixel.hitCount());
            }
        }
        System.out.println(max);
        max = Math.log10(max);
        System.out.println(max);
        for (int col = 0; col < canvas.width(); col++) {
            for (int row = 0; row < canvas.height(); row++) {
                Pixel pixel = canvas.getPixel(col, row);
                if (pixel == null) {
                    continue;
                }
                double newHitC = Math.log10(pixel.hitCount());
                newHitC = newHitC / (max);
                double multi = Math.pow(newHitC, (1 / gamma));
                int newRed = (int) (pixel.r() * multi);
                int newGreen = (int) (pixel.g() * multi);
                int newBlue = (int) (pixel.b() * multi);
                canvas.addPixel(col, row, new Pixel(newRed, newGreen, newBlue, newHitC));
            }
        }
    }

    @Override
    public Point randomPoint() {
        double x =  -1.77 + ((1.77 - (-1.77)) * ThreadLocalRandom.current().nextDouble());
        double y = -1 + ((1 - (-1)) * ThreadLocalRandom.current().nextDouble());
        return new Point(x, y);
    }

    @Override
    public Point affineTransformation() {
        return null;
    }

    @Override
    public Point rotate(Point newPoint, double theta2) {
        double x = ((newPoint.x() * Math.cos(theta2) - newPoint.y() * Math.sin(theta2)));
        double y = ((newPoint.x() * Math.sin(theta2) + newPoint.y() * Math.cos(theta2)));
        return new Point(x, y);
    }
}
