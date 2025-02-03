public abstract class Animal {
  int life;
  public Animal()
  {
    this(100); // use this constructor
  }
  public Animal(int life)
  {
    this.life = life; // use this object
  }
  
  public void eat(){
    System.out.println("Animal eats foods");
  }
  abstract public void talk();
}
 