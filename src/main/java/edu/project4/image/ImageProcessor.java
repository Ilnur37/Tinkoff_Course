package edu.project4.image;

import edu.project4.image.FractalImage;

@FunctionalInterface
public
interface ImageProcessor {
    void process(FractalImage image);
}
