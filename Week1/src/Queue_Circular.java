public class Queue_Circular {
  private int queue[];
  private int front;
  private int rear;
  private int size;

  public Queue_Circular(int size){
    this.queue = new int[size];
    this.front = 0;
    this.rear = 0;
    this.size = size;
  }

  public void enqueue(int x){
    System.out.println("Rear: " + this.rear + " Size: " + this.size + " x: " + x);
    if(this.size - 1 < this.rear){
      System.out.println("Queue Overflow");
      return;
    }
    this.queue[++this.rear % this.size] = x;
  }

  public int dequeue(){
    if(this.front == this.rear){
      System.out.println("Queue Underflow");
      return 0;
    }
    return this.queue[this.front++ % this.size];
  }

  public int peek(){
    return this.queue[this.front % this.size];
  }

  public static void main(String[] args) {
      for (int i = 0; i <= 10; i++) {
          q.enqueue(i);
      }
      for (int i = 0; i <= 10; i++) {
          System.out.println(q.dequeue());
      }
  }
}
