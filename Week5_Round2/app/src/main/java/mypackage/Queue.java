package mypackage;

import java.util.ArrayList;

public class Queue<T> extends myBuffer {

    int capacity;

    private ArrayList<T> queueElement;

    public Queue() {
        super(3);
    }

    public Queue(int size) {
        super(size);
        this.capacity = size;
        this.queueElement = new ArrayList<>(this.capacity);
    }

    public void enqueue(T item) {
        if (queueElement.size() >= capacity) {
            throw new IllegalStateException("Queue is full");
        }
        queueElement.add(item);
    }

    public T dequeue() {
        if (queueElement.isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return queueElement.remove(0);
    }

    public T peek() {
        if (queueElement.isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return queueElement.get(0);
    }

    public int size() {
        return queueElement.size();
    }
}
