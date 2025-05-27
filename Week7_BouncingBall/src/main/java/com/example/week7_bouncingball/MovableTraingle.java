package com.example.week7_bouncingball;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

class MovableTriangle extends MovableObject {
    private final Color color;

    MovableTriangle(double x, double y, double vx, double vy, double size, Color color) {
        super(x, y, vx, vy, size);
        this.color = color;
    }

    @Override public void draw(GraphicsContext gc) {
        double h = size * Math.sqrt(3) / 2;
        double[] xs = { x + size/2, x + size,   x };
        double[] ys = { y,          y + h,      y + h };
        gc.setFill(color);
        gc.fillPolygon(xs, ys, 3);
    }

    @Override public boolean contains(double px, double py) {
        return px >= x && px <= x+size && py >= y && py <= y+size;
    }
}
