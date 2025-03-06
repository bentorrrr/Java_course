package mypackage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class QueueTest {

    @Test
    void testQueue() {
        Queue<Integer> q = new Queue<>(10);
        for (int i = 0; i < 10; i++) {
            System.out.println("Adding: " + i);
            q.enqueue(i);
            System.out.println("Queue average: " + q.average());
        }
        for (int i = 0; i < 10; i++) {
            System.out.println("Queue: " + q.dequeue());
        }
        for (int i = 0; i < 10; i++) {
            System.out.println("Adding: " + i);
            q.enqueue(i);
        }
        q.showBuffer();
        System.out.println("Queue: " + q.dequeue());
    }

    @Test
    void testQueueOverflow() {
        Queue<Integer> q = new Queue<>(3);
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> q.enqueue(4));
        assertEquals("Queue is full", exception.getMessage());
    }

    @Test
    void testQueueUnderflow() {
        Queue<Integer> q = new Queue<>(3);
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> q.dequeue());
        assertEquals("Queue is empty", exception.getMessage());
    }
}
