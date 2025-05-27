package com.example.as03_network_fish_tank.Client;

import com.example.as03_network_fish_tank.Common.Fish;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiConsumer;

class FishPane extends Pane {
    private final List<Fish> fish = new ArrayList<>();
    private final Canvas canvas = new Canvas();
    private final GraphicsContext g = canvas.getGraphicsContext2D();
    private final FishClient owner;
    private final BiConsumer<Fish, String> outbound;

    FishPane(FishClient owner, BiConsumer<Fish, String> outbound) {
        this.owner = owner;
        this.outbound = outbound;
        getChildren().add(canvas);
        canvas.widthProperty().bind(widthProperty());
        canvas.heightProperty().bind(heightProperty());
        fish.add(Fish.random(canvas.getWidth(), canvas.getHeight()));
        new AnimationTimer() {
            @Override public void handle(long now) { tick(); }
        }.start();
    }

    void spawnFromServer(Fish f, String dir) {
        double w = canvas.getWidth();
        switch (dir) {
            case "RIGHT" -> {
                f.x  = -f.w;
                f.vx =  Math.abs(f.vx);
            }
            case "LEFT"  -> {
                f.x  =  w;
                f.vx = -Math.abs(f.vx);
            }
        }
        fish.add(f);
    }

    private void tick() {
        double w = canvas.getWidth();
        double h = canvas.getHeight();
        g.setFill(Color.LIGHTBLUE);
        g.fillRect(0, 0, w, h);
        Iterator<Fish> it = fish.iterator();
        while (it.hasNext()) {
            Fish f = it.next();
            f.updatePosition();
            if (f.y < 0 || f.y + f.h > h) f.vy *= -1;
            if (f.x + f.w < 0) {
                if (owner.getPeerCount() < 2) {
                    f.vx *= -1; f.x = -f.w;
                } else {
                    outbound.accept(f, "LEFT");
                    it.remove();
                    continue;
                }
            }
            if (f.x > w) {
                if (owner.getPeerCount() < 2) {
                    f.vx *= -1; f.x = w;
                } else {
                    outbound.accept(f, "RIGHT");
                    it.remove();
                    continue;
                }
            }
            f.draw(g);
        }
    }
}
