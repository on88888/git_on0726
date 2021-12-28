package day10OOP.src.com.atguigu.poly.p3;
/*

多态应用在数组： 父类类型作为数组类型 可以接受多个子类对象
 */
public class Test {
    public static void main(String[] args) {
        int [] arr  ={10,20,30};

       // double[] dArr = {3.14,10,20L};

       Animal[] as = {new Cat(),new Dog()};

        for (Animal a : as) {
            a.eat();
        }
    }
}
