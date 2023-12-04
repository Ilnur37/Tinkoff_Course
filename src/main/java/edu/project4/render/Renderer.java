package edu.project4.render;

import edu.project4.Point;
import edu.project4.image.FractalImage;
import java.util.List;

public interface Renderer {
    FractalImage render(
        FractalImage canvas,
        List<AffineTransformation> transforms,
        List<String> namesOfTrans,
        short numberOfTrans,
        int samples,
        short iterPerSample
    );

    void gammaCorrection(FractalImage canvas);

    Point rotate(Point newPoint, double theta2);

    Point randomPoint();

    Point affineTransformation();
}
