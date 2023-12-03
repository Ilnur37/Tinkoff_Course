package edu.project4;

public record Rect(double x, double y, int width, int height) {
    public boolean contains(Point p) {
        return p.x() >= 0 && p.x() < width
            && p.y() >= 0 && p.y() < height;
    }

}
