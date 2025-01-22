public class App {
    public static void main(String[] args) throws Exception {
      Stack st = new Stack(10);
      Queue q = new Queue(10);

      for (int i = 0; i < 11; i++){
        System.out.println("Adding: " + i);
        st.push(i);
        q.enqueue(i);
      }
      st.showBuffer();
      System.out.println("Stack average: " + st.average());
      q.showBuffer();
      System.out.println("Queue average: " + q.average());
      for(int i = 0; i < 10; i++){
        System.out.println("Stack: " + st.pop());
        System.out.println("Queue: " + q.dequeue());
      }
      for (int i=0; i<10; i++){
        System.out.println("Adding: " + i);
        st.push(i);
        q.enqueue(i);
      }
      st.showBuffer();
      q.showBuffer();
      for (int i = 0; i < 11; i++) {
        System.out.println("Stack: " + st.pop());
        System.out.println("Queue: " + q.dequeue());
      }
    }
}
