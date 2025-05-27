package com.example.week7_bouncingball;// com.example.week7_bouncingball.MovableObject.java
import javafx.scene.canvas.GraphicsContext;

abstract class MovableObject {
    protected double x, y;
    protected double vx, vy;
    protected double size;

    MovableObject(double x, double y, double vx, double vy, double size) {
        this.x = x; this.y = y; this.vx = vx; this.vy = vy; this.size = size;
    }

    public void update(double dt, double w, double h) {
        x += vx * dt;
        y += vy * dt;

        if (x < 0)         { x = 0;       vx = -vx; }
        if (x + size > w)  { x = w-size;  vx = -vx; }
        if (y < 0)         { y = 0;       vy = -vy; }
        if (y + size > h)  { y = h-size;  vy = -vy; }
    }

    public double getCenterX() { return x + size * 0.5; }
    public double getCenterY() { return y + size * 0.5; }
    public double getRadius()  { return size * 0.5; }


    public abstract void draw(GraphicsContext gc);

    public abstract boolean contains(double px, double py);
}
