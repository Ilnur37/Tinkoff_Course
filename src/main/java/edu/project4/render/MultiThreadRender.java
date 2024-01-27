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
import java.util.stream.Stream;
import lombok.SneakyThrows;

public class MultiThreadRender implements Render {
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
        int cores = Runtime.getRuntime().availableProcessors() / 2;
        int samplesInThread = samples / cores;
        ExecutorService executorService = Executors.newFixedThreadPool(cores);

        Callable<FractalImage> workers = () -> {
            return worker(transforms, nonlinearTrans, samplesInThread, iterPerSample, symmetry);
        };
        var tasks = Stream.generate(() -> workers).limit(cores).toList();
        List<Future<FractalImage>> images = executorService.invokeAll(tasks);
        List<FractalImage> fractalImages = new ArrayList<>();
        if (cores * samplesInThread != samples) {
            fractalImages.add(worker(
                transforms,
                nonlinearTrans,
                samples - cores * samplesInThread,
                iterPerSample,
                symmetry
            ));
        }
        for (var image : images) {
            fractalImages.add(image.get());
        }

         mergeColor(canvas, cores, fractalImages, executorService);

        executorService.shutdown();
        return canvas;
    }

    private FractalImage worker(
        List<AffineTransformation> transforms,
        List<String> nonlinearTrans,
        int samples,
        short iterPerSample,
        int symmetry
    ) {
        FractalImage tempCanvas = FractalImage.create(xImage, yImage);
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

                        Pixel pixel;
                        pixel = tempCanvas.getPixel(x, y);

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
                        tempCanvas.addPixel(x, y, pixel);
                    }
                }
            }
        }
        return tempCanvas;
    }

    @SuppressWarnings("NestedForDepth")
    private void mergeColor(
        FractalImage canvas,
        int threads,
        List<FractalImage> fractalImages,
        ExecutorService executorService
    ) throws InterruptedException, ExecutionException {
        List<Integer> startsW = new ArrayList<>();
        List<Integer> endsW = new ArrayList<>();
        int partOfW = xImage / threads;

        for (int i = 0; i < threads; i++) {
            startsW.add(partOfW * i);
            endsW.add(partOfW * (i + 1) - 1);
        }
        endsW.add(threads - 1, xImage);

        List<Callable<Void>> callForUpdateColor = new ArrayList<>();
        for (int i = 0; i < threads; i++) {
            int finalI = i;
            callForUpdateColor.add(() -> {
                for (int col = startsW.get(finalI); col < endsW.get(finalI); col++) {
                    for (int row = 0; row < yImage; row++) {
                        Pixel pixel = null;
                        int fractalsSize = fractalImages.size();
                        int j = 0;
                        while (j < fractalsSize && pixel == null) {
                            pixel = fractalImages.get(j).getPixel(col, row);
                            ++j;
                        }
                        if (pixel == null) {
                            continue;
                        }
                        double hitCount = pixel.hitCount();
                        double r = pixel.r();
                        double g = pixel.g();
                        double b = pixel.b();
                        for (; j < fractalImages.size(); j++) {
                            pixel = fractalImages.get(j).getPixel(col, row);
                            if (pixel == null) {
                                continue;
                            }
                            hitCount += pixel.hitCount();
                            r = (r + pixel.r()) / 2;
                            g = (g + pixel.g()) / 2;
                            b = (b + pixel.b()) / 2;
                        }
                        canvas.addPixel(col, row, new Pixel(r, g, b, hitCount));
                    }
                }
                return null;
            });
        }

        List<Future<Void>> updateColor = executorService.invokeAll(callForUpdateColor);
        for (var image : updateColor) {
            image.get();
        }
    }
}
