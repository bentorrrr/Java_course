public class Cat extends Animal {
  String name;
  public static String food = "mice";

  public Cat(String Name)
  {
    super(); // use super class constructor
    this.name = Name;
  }

  public Cat(String Name, int life) // overload Cat constructor
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
    meow();
  }
  void meow(){ // how cat talk.
    System.out.println("meow");
  }

  @Override
  public void eat(){
    System.out.println("Cat eats " + Cat.food);
  }

}
 