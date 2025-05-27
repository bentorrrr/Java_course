package com.example.week7_bouncingball;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

class MovableImage extends MovableObject {
    private static final Image FALLBACK;
    static {
        int size = 32;
        javafx.scene.image.WritableImage wi = new javafx.scene.image.WritableImage(size,size);
        for (int i=0;i<size;i++)for(int j=0;j<size;j++)
            wi.getPixelWriter().setColor(i,j, (i+j)%2==0? Color.BLACK: Color.TRANSPARENT);
        FALLBACK = wi;
    }

    private final Image image;
    private double angle;
    private final double angularVel;

    private static Image loadImage(String resource) {
        var url = MovableImage.class.getResource(resource);
        System.out.println("Loading image " + url);
        if (url == null) {
            System.err.println("❌ cannot find resource " + resource);
            return FALLBACK;
        }
        return new Image(url.toExternalForm());
    }

    MovableImage(double x, double y, double vx, double vy, double size,
                 String resourcePath, double angularVel) {
        super(x, y, vx, vy, size);
        this.image      = loadImage(resourcePath);
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
        gc.drawImage(image, -size/2, -size/2, size, size);
        gc.restore();
    }

    @Override public boolean contains(double px, double py) {
        return px >= x && px <= x+size && py >= y && py <= y+size;
    }
}
