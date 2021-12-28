package day12OOPAndException.src.com.atguigu.innerclass.in4.in3;

public abstract class Animal {

   abstract void eat();
}

class Dog extends Animal{

    void lookHome(){
        System.out.println("小狗看家");
    }

    @Override
    void eat() {
        System.out.println("小狗吃肉");
    }
}
