package edu.project4.image;

import edu.project4.Pixel;

public record FractalImage(Pixel[][] data, int width, int height) {

    public static FractalImage create(int width, int height) {
        Pixel[][] data = new Pixel[width][height];
        return new FractalImage(data, width, height);
    }

    public Pixel getPixel(int x, int y) {
        return data[x][y];
    }

    public void addPixel(int x, int y, Pixel pixel) {
        data[x][y] = pixel;
    }
}
