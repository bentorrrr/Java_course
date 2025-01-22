public class Queue extends myBuffer
{
    int head, tail, count, capacity;
    public Queue() {
        super(10);
    }

    public Queue(int size) {
        super(size);
        head = tail = count = 0;
        capacity = size;
    }
    
    public void enqueue (int item){
      if (count >= capacity){
        System.out.println("Queue Overflow");
        return;
      }
      items[tail++ % capacity] = item;
      count++;
    }

    public int dequeue(){
      if(count == 0){
        System.out.println("Queue Underflow");
        return -1;
      }
      count--;
      return items[head++%capacity];
    }

    public int peek(){
      return items[head % capacity];
    }
}
