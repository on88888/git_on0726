package day11OOP.src.com.atguigu.final1;

/*public final class Animal {
}*/
public  class Animal {


 //   public final void eat(){}
 public  void eat(){}
}
class Dog extends Animal{
    String name;



    public Dog(String name) {
        this.name = name;
    }

    @Override
    public void eat() {
        super.eat();
    }

    public void show(){
        System.out.println("name = "+ name);
    }
}