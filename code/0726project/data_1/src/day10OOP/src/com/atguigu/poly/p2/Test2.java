package day10OOP.src.com.atguigu.poly.p2;
/*
多态应用在形参： 可以传入 不同的子类对象
 */
public class Test2 {
    public static void main(String[] args) {

        Cat cat = new Cat();
        showEat(cat);

        Dog d = new Dog();
        showEat(d);
    }
    public static void showEat(Animal a){//Animal a = new Cat();
        a.eat();
    }
}
