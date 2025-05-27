package com.example.as03_network_fish_tank.Common;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import java.util.concurrent.ThreadLocalRandom;

public class Fish {
    public double x, y, vx, vy, w, h;
    public String image;
    private transient Image sprite;

    public Fish() {}

    public Fish(double x, double y, double vx, double vy, double w, double h, String image) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.w = w;
        this.h = h;
        this.image = image;
        var stream = Fish.class.getResourceAsStream("/" + image);
        if (stream != null) sprite = new Image(stream);
    }

    public static Fish random(double maxW, double maxH) {
        var r = ThreadLocalRandom.current();
        double size = 32;
        double boundX = maxW - size;
        double boundY = maxH - size;
        double x = (boundX > 0) ? r.nextDouble(boundX) : 0;
        double y = (boundY > 0) ? r.nextDouble(boundY) : 0;
        double vx = r.nextDouble(-2, 2);
        double vy = r.nextDouble(-2, 2);
        if (vx == 0) vx = 1;
        if (vy == 0) vy = 1;
        return new Fish(x, y, vx, vy, size, size, "fish.png");
    }

    public void updatePosition() {
        x += vx;
        y += vy;
    }

    public void draw(GraphicsContext g) {
        if (sprite == null) {
            var stream = Fish.class.getResourceAsStream("/" + image);
            if (stream != null) sprite = new Image(stream);
        }
        if (sprite != null) {
            g.drawImage(sprite, x, y, w, h);
        } else {
            g.setFill(Color.ORANGE);
            g.fillOval(x, y, w, h);
        }
    }
}
