
// public class Stack {

//   static int st[] = new int[15];
//   static int top = 0;

//   public static void main(String[] args) throws Exception {
//     System.out.println("Stack Demonstration: ");
//     for (int i = 0; i <= 10; i++) {
//         System.out.println("Pushing: " + i);
//         Stack.push(i);
//     }
//     System.out.println("");
//     for (int i = 0; i <= 10; i++) {
//         System.out.println("Popping: " + Stack.pop());
//     }
//   }

//   public static void push(int x){
//     st[++top] = x;
//   }

//   public static int pop(){
//     if (top > 0){
//       return st[top--];
//     }
//     System.out.println("Stack Underflow");
//     return 0;
//   }

// }
