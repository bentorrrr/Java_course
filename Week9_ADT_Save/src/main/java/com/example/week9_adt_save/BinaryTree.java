package com.example.week9_adt_save;

import java.io.*;
import java.util.function.Consumer;

public class BinaryTree<T extends Comparable<T>> implements Serializable {
    private static final long serialVersionUID = 1L;

    private BinaryTreeNode<T> root;

    public void insert(T data) { root = insertRec(root, data); }

    public boolean contains(T data) { return containsRec(root, data); }

    public void delete(T data) { root = deleteRec(root, data); }

    public void inorder(Consumer<T> action) { inorderRec(root, action); }

    public void preorder(Consumer<T> action) { preorderRec(root, action); }

    public void postorder(Consumer<T> action) { postorderRec(root, action); }

    public void saveToFile(String filename) throws IOException {
        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(this);
        }
    }

    @SuppressWarnings("unchecked")
    public static <E extends Comparable<E>> BinaryTree<E>
    loadFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(filename))) {
            return (BinaryTree<E>) in.readObject();
        }
    }

    private BinaryTreeNode<T> insertRec(BinaryTreeNode<T> node, T data) {
        if (node == null) return new BinaryTreeNode<>(data);

        int cmp = data.compareTo(node.getData());
        if (cmp < 0)       node.setLeft(insertRec(node.getLeft(), data));
        else if (cmp > 0)  node.setRight(insertRec(node.getRight(), data));
        return node;
    }

    private boolean containsRec(BinaryTreeNode<T> node, T data) {
        if (node == null) return false;
        int cmp = data.compareTo(node.getData());
        if (cmp == 0)       return true;
        return cmp < 0 ? containsRec(node.getLeft(), data)
                : containsRec(node.getRight(), data);
    }

    private BinaryTreeNode<T> deleteRec(BinaryTreeNode<T> node, T data) {
        if (node == null) return null;

        int cmp = data.compareTo(node.getData());
        if (cmp < 0)            node.setLeft(deleteRec(node.getLeft(), data));
        else if (cmp > 0)       node.setRight(deleteRec(node.getRight(), data));
        else {
            if (node.getLeft()  == null) return node.getRight();
            if (node.getRight() == null) return node.getLeft();
            BinaryTreeNode<T> succ = findMin(node.getRight());
            node.setData(succ.getData());
            node.setRight(deleteRec(node.getRight(), succ.getData()));
        }
        return node;
    }

    private BinaryTreeNode<T> findMin(BinaryTreeNode<T> node) {
        while (node.getLeft() != null) node = node.getLeft();
        return node;
    }

    private void inorderRec(BinaryTreeNode<T> node, Consumer<T> action) {
        if (node == null) return;
        inorderRec(node.getLeft(),  action);
        action.accept(node.getData());
        inorderRec(node.getRight(), action);
    }

    private void preorderRec(BinaryTreeNode<T> node, Consumer<T> action) {
        if (node == null) return;
        action.accept(node.getData());
        preorderRec(node.getLeft(),  action);
        preorderRec(node.getRight(), action);
    }

    private void postorderRec(BinaryTreeNode<T> node, Consumer<T> action) {
        if (node == null) return;
        postorderRec(node.getLeft(),  action);
        postorderRec(node.getRight(), action);
        action.accept(node.getData());
    }

    public BinaryTreeNode<T> getRoot() { return root; }

    public void setRoot(BinaryTreeNode<T> root) {
        this.root = root;
    }
}
