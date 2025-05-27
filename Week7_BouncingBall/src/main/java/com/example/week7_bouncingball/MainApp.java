package com.example.week7_bouncingball;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MainApp extends Application {

    private final List<MovableObject> objects = new ArrayList<>();

    private Canvas          canvas;
    private GraphicsContext gc;
    private long            lastFrameNanos = 0;

    @Override public void start(Stage stage) {
        canvas = new Canvas(800, 600);
        gc     = canvas.getGraphicsContext2D();

        BorderPane root  = new BorderPane(canvas);
        Scene      scene = new Scene(root);
        canvas.widthProperty().bind(scene.widthProperty());
        canvas.heightProperty().bind(scene.heightProperty());

        objects.add(randomCircle(canvas.getWidth() / 2, canvas.getHeight() / 2));

        scene.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                objects.add(randomObject(e.getX(), e.getY()));
            } else if (e.getButton() == MouseButton.SECONDARY) {
                for (int i = objects.size() - 1; i >= 0; i--) {
                    if (objects.get(i).contains(e.getX(), e.getY())) {
                        if (objects.size() > 1) objects.remove(i);
                        break;
                    }
                }
            }
        });

        new AnimationTimer() {
            @Override public void handle(long now) {
                if (lastFrameNanos == 0) {
                    lastFrameNanos = now;
                    return;
                }
                double dt = (now - lastFrameNanos) / 1e9;
                lastFrameNanos = now;

                double w = canvas.getWidth(), h = canvas.getHeight();

                objects.forEach(o -> o.update(dt, w, h));

                for (int i = 0; i < objects.size(); i++) {
                    for (int j = i + 1; j < objects.size(); j++) {
                        resolveCollision(objects.get(i), objects.get(j));
                    }
                }

                gc.setFill(Color.WHITE);
                gc.fillRect(0, 0, w, h);
                objects.forEach(o -> o.draw(gc));
            }
        }.start();


        stage.setTitle("Project 2 – Bouncing Objects");
        stage.setScene(scene);
        stage.show();
    }

    private MovableObject randomObject(double x, double y) {
        switch (ThreadLocalRandom.current().nextInt(4)) {
            case 0:  return randomCircle(x, y);
            case 1:  return randomRectangle(x, y);
            case 2:  return randomTriangle(x, y);
            default: return randomShuriken(x, y);
        }
    }
    private MovableCircle randomCircle(double x, double y) {
        double size = rnd(25, 60);
        return new MovableCircle(x, y, rnd(-150,150), rnd(-150,150), size,
                randomColor());
    }
    private MovableRectangle randomRectangle(double x, double y) {
        double size = rnd(30, 70);
        return new MovableRectangle(x, y, rnd(-140,140), rnd(-140,140), size,
                randomColor(), rnd(-90,90));
    }
    private MovableTriangle randomTriangle(double x, double y) {
        double size = rnd(30, 70);
        return new MovableTriangle(x, y, rnd(-160,160), rnd(-160,160), size,
                randomColor());
    }
    private MovableImage randomShuriken(double x, double y) {
        double size = rnd(40, 80);
        return new MovableImage(x, y, rnd(-180,180), rnd(-180,180), size,
                "/com/example/week7_bouncingball/Shuruken.png", rnd(-180,180));
    }
    private static Color randomColor() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        return Color.color(r.nextDouble(), r.nextDouble(), r.nextDouble());
    }
    private static double rnd(double a, double b) {
        return ThreadLocalRandom.current().nextDouble(a, b);
    }

    private static void resolveCollision(MovableObject a, MovableObject b) {
        double dx = b.getCenterX() - a.getCenterX();
        double dy = b.getCenterY() - a.getCenterY();
        double dist = Math.hypot(dx, dy);
        double minDist = a.getRadius() + b.getRadius();

        if (dist == 0 || dist >= minDist) return;

        double overlap = minDist - dist;
        double nx = dx / dist;
        double ny = dy / dist;
        a.x -= nx * overlap * 0.5;
        a.y -= ny * overlap * 0.5;
        b.x += nx * overlap * 0.5;
        b.y += ny * overlap * 0.5;

        double dvx = b.vx - a.vx;
        double dvy = b.vy - a.vy;
        double vn  = dvx * nx + dvy * ny;
        if (vn > 0) return;

        double impulse = -2 * vn / 2;
        double ix = impulse * nx;
        double iy = impulse * ny;

        a.vx -= ix;
        a.vy -= iy;
        b.vx += ix;
        b.vy += iy;
    }

    public static void main(String[] args) { launch(args); }
}
