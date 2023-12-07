package edu.project4.render;

import edu.project4.Pixel;
import edu.project4.Point;
import edu.project4.image.FractalImage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class OneThreadRender implements Renderer {
    private final static Logger LOGGER = LogManager.getLogger();
    @Override
    public FractalImage render(
        FractalImage canvas,
        List<AffineTransformation> transforms,
        List<String> namesOfTrans,
        short numberOfTrans,
        int samples,
        short iterPerSample,
        int symmetry
    ) {
        int xImage = canvas.width();
        int yImage = canvas.height();

        List<String> nonlinearTrans = Transformation.getTransformations(namesOfTrans, numberOfTrans);
        System.out.println(nonlinearTrans);

        for (int num = 0; num < samples; ++num) {
            if (num % 5000 == 0) {
                LOGGER.info("num = " + num);
            }
            Point firstPoint = randomPoint();

            for (short step = -20; step < iterPerSample; ++step) {
                int randAffineTrans = ThreadLocalRandom.current().nextInt(transforms.size());
                AffineTransformation affineTrans = transforms.get(randAffineTrans);
                Point affinePoint = affineTransformation(affineTrans, firstPoint);

                int randTrans = ThreadLocalRandom.current().nextInt(nonlinearTrans.size());
                Point newPoint = Transformation.getFunction(nonlinearTrans.get(randTrans)).apply(affinePoint);
                if (step > 0) {
                    double theta = 0.0;
                    for (int s = 0; s < symmetry; theta += Math.PI * 2 / symmetry, ++s) {
                        Point pwr = rotate(newPoint, theta);
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
        }
        return canvas;
    }

    @Override
    public void gammaCorrection(FractalImage canvas) {
        double max = 0.0;
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
        max = Math.log10(max);
        for (int col = 0; col < canvas.width(); col++) {
            for (int row = 0; row < canvas.height(); row++) {
                Pixel pixel = canvas.getPixel(col, row);
                if (pixel == null) {
                    continue;
                }
                double newHitC = Math.log10(pixel.hitCount());
                newHitC = newHitC / max * 3;
                double multi = Math.pow(newHitC, (1 / gamma));
                int newRed = (int) (pixel.r() * multi);
                int newGreen = (int) (pixel.g() * multi);
                int newBlue = (int) (pixel.b() * multi);

                canvas.addPixel(col, row, new Pixel(newRed, newGreen, newBlue, newHitC));
            }
        }
    }
}