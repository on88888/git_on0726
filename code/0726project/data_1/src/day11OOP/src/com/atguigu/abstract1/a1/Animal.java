package day11OOP.src.com.atguigu.abstract1.a1;

public abstract  class Animal {


   public abstract void eat();
/*    public void eat(){
        System.out.println("动物吃饭");
    }*/
}
class Dog extends Animal{
    @Override
    public void eat() {
        System.out.println("小狗吃骨头");
    }

    /*@Override
    public void eat() {
        System.out.println("小狗吃骨头");
    }*/
}

class Cat extends Animal{

    @Override
    public void eat() {
        System.out.println("小猫 吃鱼");
    }
}