package edu.project4.image;

import edu.project4.Pixel;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Locale;

public final class ImageUtils {
    private ImageUtils() {
    }

    public static void save(FractalImage image, Path filename, ImageFormat format) {
        int width = image.width();
        int height = image.height();

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Pixel pixel = image.getPixel(x, y);
                int rgb;
                if (pixel == null) {
                    rgb = (0);
                } else {
                    rgb = (pixel.r() << 16) | (pixel.g() << 8) | pixel.b();
                }
                bufferedImage.setRGB(x, y, rgb);
            }
        }

        try {
            ImageIO.write(bufferedImage, format.name().toLowerCase(Locale.ROOT), filename.toFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
