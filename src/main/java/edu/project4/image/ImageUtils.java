package edu.project4.image;

import edu.project4.Pixel;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Locale;
import javax.imageio.ImageIO;

public final class ImageUtils {
    private ImageUtils() {
    }

    @SuppressWarnings("MagicNumber")
    public static void save(FractalImage image, Path filename, ImageFormat format) {
        BufferedImage bufferedImage = new BufferedImage(image.width(), image.height(), BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < image.height(); y++) {
            for (int x = 0; x < image.width(); x++) {
                Pixel pixel = image.getPixel(x, y);
                int rgb;
                if (pixel == null) {
                    continue;
                } else {
                    rgb = ((int) pixel.r() << 16) | ((int) pixel.g() << 8) | (int) pixel.b();
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
