public class Human extends Animal {
  String name;
  public static String food = "Meat & Vegetables";

  public Human(String Name)
  {
    super(); // use super class constructor
    this.name = Name;
  }

  public Human(String Name, int life) // overload Human constructor
  {
    super(life); // also use super class constructor
    this.name = Name;
  }

  public String getName(){
    return this.name;
  }

  public void setName(String name){
    this.name = name;
  }

  @Override
  public void talk() {
    System.out.println("Hello, I am " + this.name);
  }

  @Override
  public void eat(){
    System.out.println("Human eats " + Human.food);
  }


}

