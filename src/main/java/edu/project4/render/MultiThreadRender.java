package edu.project4.render;

import edu.project4.Pixel;
import edu.project4.Point;
import edu.project4.image.FractalImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Stream;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MultiThreadRender implements Renderer {
    private final static Logger LOGGER = LogManager.getLogger();
    private static final ReadWriteLock LOCK = new ReentrantReadWriteLock();
    private static final Lock READ_LOCK = LOCK.readLock();
    private static final Lock WRITE_LOCK = LOCK.writeLock();
    private static final int WAITING_TIME = 200;
    private int xImage;
    private int yImage;

    @Override
    @SneakyThrows({ExecutionException.class, InterruptedException.class})
    public FractalImage render(
        FractalImage canvas,
        List<AffineTransformation> transforms,
        List<String> namesOfTrans,
        short numberOfTrans,
        int samples,
        short iterPerSample,
        int symmetry
    ) {
        xImage = canvas.width();
        yImage = canvas.height();

        List<String> nonlinearTrans = Transformation.getTransformations(namesOfTrans, numberOfTrans);
        System.out.println(nonlinearTrans);

        int cores = 5;
        int samplesInThread = samples / cores;
        ExecutorService executorService = Executors.newFixedThreadPool(cores);
        Callable<FractalImage> workers = () -> {
            worker(canvas, transforms, nonlinearTrans, samplesInThread, iterPerSample, symmetry);
            return null;
        };
        var tasks = Stream.generate(() -> workers).limit(cores).toList();
        List<Future<FractalImage>> futures = executorService.invokeAll(tasks);
        List<FractalImage> fractalImages = new ArrayList<>();
        if (cores * samplesInThread != samples) {
            fractalImages.add(worker(
                canvas,
                transforms,
                nonlinearTrans,
                samples - cores * samplesInThread,
                iterPerSample,
                symmetry
            ));
        }
        for (var future : futures) {
            fractalImages.add(future.get());
        }
        executorService.shutdown();
        return canvas;
    }

    //@SneakyThrows(InterruptedException.class)
    private FractalImage worker(
        FractalImage canvas,
        List<AffineTransformation> transforms,
        List<String> nonlinearTrans,
        int samples,
        short iterPerSample,
        int symmetry
    ) {
        for (int num = 0; num < samples; ++num) {
            if (num % 1000 == 0) {
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

                        Pixel pixel = null;
                        pixel = canvas.getPixel(x, y);

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
