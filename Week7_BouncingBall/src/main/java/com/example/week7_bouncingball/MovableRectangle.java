package com.example.week7_bouncingball;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

class MovableRectangle extends MovableObject {
    private final Color color;
    private double angle;
    private final double angularVel;

    MovableRectangle(double x, double y, double vx, double vy, double size,
                     Color color, double angularVel) {
        super(x, y, vx, vy, size);
        this.color = color;
        this.angularVel = angularVel;
    }

    @Override public void update(double dt, double w, double h) {
        super.update(dt, w, h);
        angle = (angle + angularVel * dt) % 360;
    }

    @Override public void draw(GraphicsContext gc) {
        gc.save();
        gc.translate(x + size/2, y + size/2);
        gc.rotate(angle);
        gc.setFill(color);
        gc.fillRect(-size/2, -size/2, size, size);
        gc.restore();
    }

    @Override public boolean contains(double px, double py) {
        return px >= x && px <= x+size && py >= y && py <= y+size;
    }
}
