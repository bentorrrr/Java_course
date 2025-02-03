public class App {
    public static void main(String[] args) throws Exception {
      Dog d;
      Cat c;
      Human h;
      Animal[] a = new Animal[10];
      a[0] = d = new Dog("Goofy");
      a[1] = c = new Cat("Tom");
      a[2] = h = new Human("John");

      // d.eat(c);
      // ((Dog) a[0]).bark();
      // a[0].talk();

      for (Animal animal : a) {
        if( animal instanceof Cat cat ){
          cat.meow();
          cat.eat();
        } else if ( animal instanceof Dog dog ){ 
          dog.bark();
          dog.eat();
        }
        else if ( animal instanceof Human human ){
          human.talk();
          human.eat();
        }
        else{
          if (animal != null) {
            animal.talk();
          }
        }
      }

    }
}
