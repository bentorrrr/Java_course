package com.example.week9_adt_save;
import java.io.Serializable;

/**
 * One node of a generic Binary Search Tree.
 */
public class BinaryTreeNode<T extends Comparable<T>> implements Serializable {
    private static final long serialVersionUID = 1L;

    private T data;
    private BinaryTreeNode<T> left, right;

    public BinaryTreeNode(T data) {
        this.data = data;
    }

    /* ---------- accessors ---------- */
    public T getData()                           { return data; }
    public void setData(T data)                  { this.data = data; }

    public BinaryTreeNode<T> getLeft()           { return left; }
    public void setLeft(BinaryTreeNode<T> left)  { this.left = left; }

    public BinaryTreeNode<T> getRight()          { return right; }
    public void setRight(BinaryTreeNode<T> right){ this.right = right; }

    @Override public String toString() { return String.valueOf(data); }
}

