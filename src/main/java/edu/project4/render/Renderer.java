package edu.project4.render;

import edu.project4.Pixel;
import edu.project4.Point;
import edu.project4.Rect;
import edu.project4.image.FractalImage;

public interface Renderer {
    default FractalImage render(
        FractalImage canvas,
        Rect rect,
        //List<Transformation> variations,
        int samples,
        short iterPerSample
        //long seed
    ) {
        int xImage = canvas.width();
        int yImage = canvas.height();
        double xMax = 1.77;
        double xMin = -1.77;
        double yMax = 1;
        double yMin = -1;

        for (int num = 0; num < samples; ++num) {
            Point pw = randomPoint(rect);

            for (short step = 0; step < iterPerSample; ++step) {
                //Transformation variation = random(variations, ...);
                //double[] transforms = createTransforms();
                double[] transforms = {0.2, -1.26, 0.223, 0.2422, 0, 1.23};
                double xNew = transforms[0] * pw.x() + transforms[1] * pw.y() + transforms[2];
                double yNew = transforms[3] * pw.x() + transforms[4] * pw.y() + transforms[5];
                Transformation variation = point -> {
                    double denominator = (1.0 / (xNew * xNew) + (yNew * yNew));
                    return new Point(xNew * denominator, yNew * denominator);
                };
                Point newPoint = variation.apply(pw);

                double theta2 = 0.0;
                int symmetry = 1;
                for (int s = 0; s < symmetry; theta2 += Math.PI * 2 / symmetry, ++s) {
                    Point pwr = rotate(newPoint, theta2);
                    if (!(pwr.x() >= xMin && pwr.x() <= xMax
                        && pwr.y() >= yMin && pwr.y() <= yMax)) {
                        continue;
                    }
                    int x = (int) (xImage - (((xMax - pwr.x()) / xMax - xMin) * xImage));
                    int y = (int) (yImage - (((yMax - pwr.y()) / yMax - yMin) * yImage));
                    //fractal->xres - (unsigned int) (((fractal->xmax - x_rot) / fractal->ranx) * fractal->xres);
                    if (!(x >= 0 && x < xImage
                        && y >= 0 && y < yImage)) {
                        continue;
                    }
                    Pixel pixel = canvas.getPixel(x, y);
                    if (pixel == null) {
                        pixel = new Pixel(Pixel.randomColor(), Pixel.randomColor(), Pixel.randomColor(), 1);
                    } else {
                        pixel = new Pixel(
                            (pixel.r() + Pixel.randomColor()) / 2,
                            (pixel.r() + Pixel.randomColor()) / 2,
                            (pixel.r() + Pixel.randomColor()) / 2,
                            pixel.hitCount() + 1
                        );
                    }
                    canvas.addPixel(x, y, pixel);
                }
            }
        }
        return canvas;
    }

    double[] createTransforms();

    Point rotate(Point newPoint, double theta2);

    Point randomPoint(Rect world);

}
