public class Stack extends myBuffer
{
  int top;
  public Stack() {
    super(10);
  }
  
  public Stack(int size){
    super(size);
    top = -1;
  }

  public void push(int item){
    if (top == items.length - 1){
      System.out.println("Stack Overflow");
      return;
    }
    items[++top] = item;
  }

  public int pop(){
    if (top == -1){
      System.out.println("Stack Underflow");
      return -1;
    }
    return items[top--];
  }

  public int getTop(){
    return items
  }
}
