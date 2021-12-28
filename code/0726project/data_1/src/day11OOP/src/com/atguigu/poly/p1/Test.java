package day11OOP.src.com.atguigu.poly.p1;
/*
1. 有继承
2. 有方法重写
   父类的引用 指向子类的实例 弊端：无法使用子类自己新增的方法

想要使用子类自己新增的方法： 必须向下转型才可以
int n = 10;
double d = n;
int m = (int)d;
小的数据类型 变量名 = (小的数据类型)大的数据类型的值；

注意：
    1.在进行向下转型时  可能出现的问题 ClassCastException
    2. 使用 instanceof 解决
 */
public class Test {
    public static void main(String[] args) {
        Dog d = new Dog();

        showEat(d);

        Cat c = new Cat();

        showEat(c);

    }
    public static void showEat(Animal a){
        a.eat();
        //小的数据类型 变量名 = (小的数据类型)大的数据类型的值；
        if( a instanceof Dog){
            Dog d = (Dog)a;
            d.lookHome();
        }else if(a instanceof Cat){
            Cat c = (Cat)a;
            c.catchMouse();
        }
    }
}
