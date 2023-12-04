package edu.project4;

import edu.project4.image.FractalImage;
import edu.project4.image.ImageFormat;
import edu.project4.image.ImageUtils;
import edu.project4.render.OneThreadRender;
import edu.project4.render.AffineTransformation;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class AppRunner {
    public static void main(String[] args) {
        OneThreadRender oneThreadRender = new OneThreadRender();
        int width = 2200;
        int height = 1350;
        FractalImage fractalImage = FractalImage.create(width, height);
        List<String> names = new ArrayList<>(List.of("heart", "spiral", "spiral"));
        short num = 3;
        int samples = 50000;
        short iterPerSample = 2000;
        List<AffineTransformation> transforms = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            transforms.add(new AffineTransformation());
        }

        FractalImage newfractalImage = oneThreadRender.render(fractalImage, transforms, names, num, samples, iterPerSample);
        Path filePath2 = Path.of("src/main/resources/project4/image2.png");
        ImageFormat format2 = ImageFormat.PNG;

        ImageUtils.save(newfractalImage, filePath2, format2);
        oneThreadRender.gammaCorrection(newfractalImage);

        Path filePath = Path.of("src/main/resources/project4/image1.png");
        ImageFormat format = ImageFormat.PNG;

        ImageUtils.save(newfractalImage, filePath, format);
    }

}
