package day09OOP.src.com.atguigu.encap.exer;

public class Test {

    public static void main(String[] args) {

        Dog d = new Dog();
        d.setName("旺财");
        d.setColor("黄色");
        d.setAge(3);

        System.out.println("名字："+d.getName()+",颜色："+d.getColor()+",年龄："+d.getAge());

    }
}
