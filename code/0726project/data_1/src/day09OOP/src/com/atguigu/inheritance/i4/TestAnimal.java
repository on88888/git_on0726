package day09OOP.src.com.atguigu.inheritance.i4;

public class TestAnimal {
    public static void main(String[] args) {
        Dog d = new Dog();
        d.name="旺财";
        d.color="黄色";
        String info = d.getInfo();

        System.out.println("info = " + info);
    }
}
