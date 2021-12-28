package day17Container.src.com.atguigu.generic.g4;

public class Test {

    public static void main(String[] args) {


        Person<Animal> p1 = new Person<>("张三", new Animal("金刚"));


        System.out.println(p1);
        Person<Ghost>p2 = new Person<>("宁采臣", new Ghost("小倩"));

        System.out.println("p2 = " + p2);


    }

}
