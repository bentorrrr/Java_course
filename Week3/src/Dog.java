public class Dog extends Animal {
  private String name;
  public static String food = "cat";
  // @Override Animal talk;

  public Dog(String Name)
  {
    super(); // use super class constructor
    this.name = Name;
  }
  public Dog(String Name, int life) // overload Dog constructor
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
    bark();
  }

  void bark(){ 
    System.out.println("Bowwow");
  }

  @Override
  public void eat(){
    System.out.println("Dog eats " + Dog.food);
  }
  public void eat(Cat c){
    System.out.println(this.name + " eats " + c.getName());
  }
}