package edu.project4.render;

import edu.project4.Pixel;
import edu.project4.Point;
import edu.project4.image.FractalImage;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class OneThreadRender implements Render {

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

        for (int num = 0; num < samples; ++num) {
            Point firstPoint = randomPoint();

            for (short step = START_STEP; step < iterPerSample; ++step) {
                int randAffineTrans = ThreadLocalRandom.current().nextInt(transforms.size());
                AffineTransformation affineTrans = transforms.get(randAffineTrans);
                Point affinePoint = affineTransformation(affineTrans, firstPoint);

                int randTrans = ThreadLocalRandom.current().nextInt(nonlinearTrans.size());
                Point newPoint = Transformation.getFunction(nonlinearTrans.get(randTrans)).apply(affinePoint);
                if (step > 0) {
                    double theta = 0.0;
                    for (int s = 0; s < symmetry; theta += Math.PI * 2 / symmetry, ++s) {
                        Point pwr = rotate(newPoint, theta);
                        if (!(pwr.x() >= X_MIN && pwr.x() <= X_MAX
                            && pwr.y() >= Y_MIN && pwr.y() <= Y_MAX)) {
                            continue;
                        }
                        int x = (xImage - (int) (((X_MAX - pwr.x()) / (X_MAX - X_MIN)) * xImage));
                        int y = (yImage - (int) (((Y_MAX - pwr.y()) / (Y_MAX - Y_MIN)) * yImage));

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
}
