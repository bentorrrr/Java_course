package com.example.week7_bouncingball;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

class MovableCircle extends MovableObject {
    private final Color color;

    MovableCircle(double x, double y, double vx, double vy, double size, Color color) {
        super(x, y, vx, vy, size);
        this.color = color;
    }

    @Override public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillOval(x, y, size, size);
    }
    @Override public boolean contains(double px, double py) {
        double cx = x + size/2, cy = y + size/2;
        double dx = px - cx,    dy = py - cy;
        return dx*dx + dy*dy <= (size/2)*(size/2);
    }
}
