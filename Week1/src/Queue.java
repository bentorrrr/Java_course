public class Queue {

  private int q[];
  private int front;
  private int rear;
  private int size;
  private LinkedList list[];


  public Queue(int size) {
    this.q = new int[size];
    this.front = -1;
    this.rear = -1;
    this.size = size;
    this.list[] = new LinkedList[size];
  }

  public enqueue(int x) {
    if (rear == size - 1){
      System.out.println("Queue Overflow");
    }
    else {
      list[++rear].data = x;
    }
  }


  public static void main(String[] args) {
    
  }
  

}
