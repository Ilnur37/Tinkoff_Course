package edu.project4;

import edu.project4.image.FractalImage;
import edu.project4.image.ImageFormat;
import edu.project4.image.ImageUtils;
import edu.project4.render.AffineTransformation;
import edu.project4.render.MultiThreadRender;
import edu.project4.render.OneThreadRender;
import edu.project4.render.Render;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("MagicNumber")
public class AppRunner {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String ENTER_NUM = "Введите число от 1 до ";
    private static final int WIDTH = 2560;
    private static final int HEIGHT = 1440;
    private static final short MAX_SHORT = 10000;
    private static final int MAX_INT = 50000000;
    private static final Scanner IN = new Scanner(System.in);
    private static final FractalImage FRACTAL_IMAGE = FractalImage.create(WIDTH, HEIGHT);
    private static final List<AffineTransformation> TRANSFORMS = new ArrayList<>();
    private static final Path PATH_ORIGINAL_IMAGE = Path.of("src/main/resources/project4/original/OriginalImage 1.bmp");
    private static final Path PATH_GAMMA_IMAGE = Path.of("src/main/resources/project4/gamma/GammaImage 1.bmp");
    private static short numOfAffine = 8;
    private static short numOfNonLin = 2;
    private static int samples = 400000;
    private static short iterPerSample = 1000;

    private AppRunner() {

    }

    @SuppressWarnings("InnerAssignment")
    @SneakyThrows({ExecutionException.class, InterruptedException.class})
    public static void run() {
        LOGGER.info("Выберите вариант работы программы:\t\t1. Многопоточный\t\t2. Однопоточный");
        short typeProgram = chooseVariant((short) 0);
        Render render = null;
        if (typeProgram == 1) {
            render = new MultiThreadRender();
        } else {
            render = new OneThreadRender();
        }

        LOGGER.info("Вы хотите сами ввести параметры?\t\t1. Да\t\t2. Нет");
        short isDefaultParameters = chooseVariant((short) 0);
        if (isDefaultParameters == 2) {
            runDefaultRender(render);
            return;
        }

        LOGGER.info("Введите количество афинных преобразований");
        numOfAffine = enterShortNum((short) -1);
        for (int i = 0; i < numOfAffine; i++) {
            TRANSFORMS.add(new AffineTransformation());
        }

        LOGGER.info("Введите названия нелинейных преобразований, которые хотели бы использовать");
        IN.nextLine();
        String namesInput = IN.nextLine();
        List<String> names = new ArrayList<>(List.of(namesInput.split(" ")));

        LOGGER.info("Введите количество нелинейных преобразований, которые хотели бы использовать");
        numOfNonLin = enterShortNum((short) -1);

        LOGGER.info("Введите количество сэмплов");
        samples = enterIntNum(-1);

        LOGGER.info("Введите количество итеаций");
        iterPerSample = enterShortNum((short) -1);

        LOGGER.info("Выберите формат изображения\t\t1. JPEG\t\t2. BMP\t\t3. PNG");
        int form = IN.nextInt();
        while (form < 1 || form > 3) {
            LOGGER.info("Введите число 1, 2 или 3");
            form = IN.nextInt();
        }
        ImageFormat format;
        switch (form) {
            case (1) -> format = ImageFormat.JPEG;
            case (2) -> format = ImageFormat.BMP;
            default -> format = ImageFormat.PNG;
        }

        LOGGER.info("Изображение должно быть симметричным?\t\t1. Нет\t\t2. Да");
        short symmetry = chooseVariant((short) 0);

        FractalImage newFractalImage =
            render.render(FRACTAL_IMAGE, TRANSFORMS, names, numOfNonLin, samples, iterPerSample, symmetry);

        ImageUtils.save(newFractalImage, PATH_ORIGINAL_IMAGE, format);
        render.gammaCorrection(newFractalImage);

        ImageUtils.save(newFractalImage, PATH_GAMMA_IMAGE, format);
    }

    private static short chooseVariant(short variant) {
        short tempVariant = variant;
        while (tempVariant != 1 && tempVariant != 2) {
            LOGGER.info("Введите 1 или 2");
            tempVariant = IN.nextShort();
        }
        return tempVariant;
    }

    private static short enterShortNum(short num) {
        short tempNum = num;
        while (tempNum < 0 || tempNum > MAX_SHORT) {
            LOGGER.info(ENTER_NUM + MAX_SHORT);
            tempNum = IN.nextShort();
        }
        return tempNum;
    }

    private static int enterIntNum(int num) {
        int tempNum = num;
        while (tempNum < 0 || tempNum > MAX_INT) {
            LOGGER.info(ENTER_NUM + MAX_INT);
            tempNum = IN.nextInt();
        }
        return tempNum;
    }

    @SneakyThrows({ExecutionException.class, InterruptedException.class})
    private static void runDefaultRender(Render render) {
        List<String> names = new ArrayList<>(List.of(""));
        for (int i = 0; i < numOfAffine; i++) {
            TRANSFORMS.add(new AffineTransformation());
        }

        FractalImage newFractalImage =
            render.render(FRACTAL_IMAGE, TRANSFORMS, names, numOfNonLin, samples, iterPerSample, 1);

        ImageFormat format = ImageFormat.BMP;

        ImageUtils.save(newFractalImage, PATH_ORIGINAL_IMAGE, format);
        render.gammaCorrection(newFractalImage);

        ImageUtils.save(newFractalImage, PATH_GAMMA_IMAGE, format);
    }
}
