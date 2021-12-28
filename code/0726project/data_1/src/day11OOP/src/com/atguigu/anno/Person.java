package day11OOP.src.com.atguigu.anno;

@Deprecated
public class Person {

    void  eat(){
        System.out.println("人吃饭");
    }
}

class Student extends Person{

    @Override
    void eat() {
        super.eat();
    }
}