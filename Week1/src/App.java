class App {
  
  public static void main(String[] args) throws Exception {
      System.out.println("Hello, World!");
      System.out.println("The sum is : " + calculator(2, 3));

      StackNonStatic stack = new StackNonStatic();  // Instantiate `stack` because its methods are non-static
      // No need to instantiate `stack` because its methods are static
      for (int i = 0; i < 10; i++) {
          stack.push(i);
      }

      for (int i = 0; i < 10; i++) {
          System.out.println(stack.pop());
      }
  }

  public static int calculator(int x, int y) {
      int Sum = x + y;
      return Sum;
  }
}
