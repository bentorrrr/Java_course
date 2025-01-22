public class Queue_Static {
  static int queue[] = new int[10];
  static int front = 0;
  static int rear = 0;
  static int size = 10;

  public static void enqueue(int x){
    if(size - 1 < rear){
      rear = 0;
    }
    queue[rear++ % size] = x;
  }

  public static int dequeue(){
    if(size - 1 < front){
      front = 0;
    }
    return queue[front++ % size];
  }

  public static int peek(){
    return queue[front % size];
  }

  public static void main(String[] args) {
      System.out.println("Queue Demonstration: ");
      for (int i = 0; i < 10; i++) {
          System.err.println("Enqueuing: " + i);
          enqueue(i);
      }
      System.out.println("");
      for (int i = 0; i < 10; i++) {
          System.out.println("Dequeuing: " + dequeue());
      }
      System.out.println("");
      for (int i = 0; i < 10; i++) {
        System.out.println("Enqueuing: " + (10 + i));
        enqueue(10 + i);
      }
      System.out.println("");
      for (int i = 0; i < 10; i++) {
          System.out.println("Dequeuing: " + dequeue());
      }
  }
}
