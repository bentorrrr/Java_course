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
      try {
          if (count >= capacity){
              throw new Exception("Queue Overflow");
          }
          items[tail++ % capacity] = item;
          count++;
      } catch (Exception e) {
          System.out.println(e.getMessage());
      }
      // if (count >= capacity){
      //   System.out.println("Queue Overflow");
      //   return;
      // }
      // items[tail++ % capacity] = item;
      // count++;
    }

    public int dequeue(){
      try {
          if (count == 0){
              throw new Exception("Queue Underflow");
          }
          count--;
          return items[head++ % capacity];
      } catch (Exception e) {
          System.out.println(e.getMessage());
          return -1;
      }
      // if(count == 0){
      //   System.out.println("Queue Underflow");
      //   return -1;
      // }
      // count--;
      // return items[head++%capacity];
    }

    public int peek(){
      return items[head % capacity];
    }
}
