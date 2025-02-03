public class Account {
  static double interest = 0.;
  public double balance;
  public void showInterest()
  {
    System.out.println("interest is " + balance*Account.interest);
  }  
}

