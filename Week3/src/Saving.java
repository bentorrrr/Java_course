public class Saving extends Account {
  static double interest = 0.5/100.;
  @Override
  public void showInterest()
  {
    System.out.println("interest is "+balance*Saving.interest);
  }
}
 