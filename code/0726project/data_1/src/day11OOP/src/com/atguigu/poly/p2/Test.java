package day11OOP.src.com.atguigu.poly.p2;
/*
多态的向上转型 只和方法有关 与属性无关
 想要使用子类的属性 需要进行向下转型
 */
public class Test {
    public static void main(String[] args) {

        Person p = new Student();
        //子类重写父类的方法 以及从父类继承的资源
        p.eat();
        System.out.println(p.name);
        //使用子类独有（新增）资源

        Student s = (Student)p;
        System.out.println(s.name);


    }
}
