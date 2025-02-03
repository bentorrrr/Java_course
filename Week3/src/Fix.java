public class Fix extends Account {
  static double interest = 2.0/100;  
  @Override
  public void showInterest()
  {
    System.out.println("interest is " + balance*Fix.interest);
  }
}