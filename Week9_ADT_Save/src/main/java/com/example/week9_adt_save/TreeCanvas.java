package com.example.week9_adt_save;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class TreeCanvas<T extends Comparable<T>> extends Canvas {
    private final BinaryTree<T> tree;
    private T highlight;

    public TreeCanvas(BinaryTree<T> tree) {
        super(900, 500);
        this.tree = tree;
    }

    public void setHighlight(T v) { highlight = v; redraw(); }

    public void redraw() {
        GraphicsContext g = getGraphicsContext2D();
        g.clearRect(0, 0, getWidth(), getHeight());
        draw(g, tree.getRoot(), getWidth()/2, 40, getWidth()/4);
    }

    private void draw(GraphicsContext gc, BinaryTreeNode<T> n,
                      double x, double y, double xOff) {
        if (n == null) return;

        /* edges */
        if (n.getLeft() != null)
            gc.strokeLine(x, y, x - xOff, y + 60);
        if (n.getRight() != null)
            gc.strokeLine(x, y, x + xOff, y + 60);

        /* node */
        gc.setFill(n.getData().equals(highlight) ? Color.ORANGE : Color.LIGHTBLUE);
        gc.fillOval(x-15, y-15, 30, 30);
        gc.setStroke(Color.BLACK);
        gc.strokeOval(x-15, y-15, 30, 30);
        gc.strokeText(n.getData().toString(), x-4, y+5);

        draw(gc, n.getLeft(),  x - xOff, y + 60, xOff/2);
        draw(gc, n.getRight(), x + xOff, y + 60, xOff/2);
    }
}
