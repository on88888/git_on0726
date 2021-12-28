package day11OOP.src.com.atguigu.object.getclass;
/*
getClass:获取运行时类型
 */
public class Test {
    public static void main(String[] args) {

        Animal a = new Dog();
        Class c = a.getClass();// com.atguigu.object.getclass.Dog

        System.out.println("c = " + c);

    }
}
class Animal{

}
class Dog extends Animal{}

