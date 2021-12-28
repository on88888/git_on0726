package day10OOP.src.com.atguigu.poly.p1;
/*
多态： 一个对象的多种形态
  看山是山 看水是水
      Person p = new Person();
      Dog d = new Dog();
  看山不是山  看水不是水
     Animal a = new Cat();
 */
public class Test {
    public static void main(String[] args) {

        China china = new China();
        showEat(china);
        Japan japan = new Japan();
        showEat(japan);
        Russia ru = new Russia();
        showEat(ru);

    }
    public static void showEat(Programmer pro){//Programmer pro = new China();;
        pro.eat();
    }
    /*public static void showEat(China ch){
        ch.eat();
    }
    public static void showEat(Japan ja){
        ja.eat();
    }*/
}
