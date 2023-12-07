package edu.project4;

import edu.project4.image.FractalImage;
import edu.project4.image.ImageFormat;
import edu.project4.image.ImageUtils;
import edu.project4.render.AffineTransformation;
import edu.project4.render.MultiThreadRender;
import edu.project4.render.OneThreadRender;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class AppRunner {
    public static void main(String[] args) {
        //OneThreadRender oneThreadRender = new OneThreadRender();
        MultiThreadRender multiThreadRender = new MultiThreadRender();
        int width = 2560;
        int height = 1440;
        FractalImage fractalImage = FractalImage.create(width, height);
        List<String> names = new ArrayList<>(List.of());
        short num = 2;
        int samples = 100000;
        short iterPerSample = 1000;
        List<AffineTransformation> transforms = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            transforms.add(new AffineTransformation());
        }

        long startTime = System.nanoTime();
        //FractalImage newFractalImage = oneThreadRender.render(fractalImage, transforms, names, num, samples, iterPerSample, 1);
        FractalImage newFractalImage = multiThreadRender.render(fractalImage, transforms, names, num, samples, iterPerSample, 1);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println(duration / 1000000 + " ms");
        Path filePath2 = Path.of("src/main/resources/project4/original/r-s-2-10.bmp");
        ImageFormat format2 = ImageFormat.BMP;

        ImageUtils.save(newFractalImage, filePath2, format2);
        //oneThreadRender.gammaCorrection(newFractalImage);
        multiThreadRender.gammaCorrection(newFractalImage);

        Path filePath = Path.of("src/main/resources/project4/gamma/r-s-2-10.bmp");
        ImageFormat format = ImageFormat.BMP;

        ImageUtils.save(newFractalImage, filePath, format);
    }
}
