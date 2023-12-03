package edu.project4;

import edu.project4.image.FractalImage;
import edu.project4.image.ImageFormat;
import edu.project4.image.ImageUtils;
import edu.project4.render.OneThreadRender;
import java.nio.file.Path;

public class AppRunner {
    public static void main(String[] args) {
        OneThreadRender oneThreadRender = new OneThreadRender();
        int width = 1980;
        int height = 1080;
        FractalImage fractalImage = FractalImage.create(width, height);
        Rect rect = new Rect(0, 0, width, height);
        int samples = 100000;
        short iterPerSample = 1500;
        /*double[][] transforms = {
            {0.2, -1.26, 0.223, 0.2422, 0, 1.23},
            {-.15, 0.28, 0.2336, 0.244, 0, 0.4455},
            {0.2, 0.25, 0.223, 0.244, 0, 1.23},
            {-0.15, -1.26, 0.2336, 0.2422, 0, 0.445}
        };*/

        FractalImage newfractalImage = oneThreadRender.render(fractalImage, rect, samples, iterPerSample);

        Path filePath = Path.of("src/main/resources/project4/image.png");
        ImageFormat format = ImageFormat.PNG;

        ImageUtils.save(newfractalImage, filePath, format);
    }

    private static double[][] transforms = {
        {0.2, -1.26, 0.223, 0.2422, 0, 1.23},
        {-.15, 0.28, 0.2336, 0.244, 0, 0.4455},
        {0.2, 0.25, 0.223, 0.244, 0, 1.23},
        {-0.15, -1.26, 0.2336, 0.2422, 0, 0.445}
        /*{0.5, 0.0, 0.0, 0.5, 0.0, 0.0},
        {0.5, 0.0, 0.0, 0.5, 0.5, 0.0},
        {0.5, 0.0, 0.0, 0.5, 0.25, 0.43},
        {0.5, 0.0, 0.0, 0.5, 0.25, -0.43}*/
        // Добавьте другие преобразования, если нужно
    };

}
