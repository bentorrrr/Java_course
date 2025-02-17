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
    try {
        items[++top] = item;
    } catch (Exception e) {
        top = items.length - 1;
        System.out.println("Stack Overflow");
    }
  }
  public int pop(){
    try {
        return items[top--];
    } catch (Exception e) {
        System.out.println("Stack Underflow");
        return -1;
    }
  }

  public int getTop(){
    return items[top];
  }
}
