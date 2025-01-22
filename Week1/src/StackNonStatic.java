public class StackNonStatic {
  int st[];
  int top;

  public StackNonStatic() {
    this.st = new int[15];
    this.top = -1;
  }

  public void main(String[] args) {
      
  }

  public void push(int x){
    this.st[++this.top] = x;
  }

  public int pop(){
    if (this.top > 0){
      return(this.st[this.top--]);
    }
    System.out.println("Stack Underflow");
    return 0;
  }
}
