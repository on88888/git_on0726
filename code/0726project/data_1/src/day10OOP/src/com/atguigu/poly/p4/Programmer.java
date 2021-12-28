package day10OOP.src.com.atguigu.poly.p4;

public class Programmer {
    public void eat(){
        System.out.println("程序员吃饭.....");
    }
}
class China extends Programmer{
    @Override
    public void eat() {
        System.out.println("中国人 吃 饺子 .....");
    }
}
class Japan extends Programmer{
    @Override
    public void eat() {
        System.out.println("日本 吃寿司......");
    }
}

class Russia extends Programmer{
    @Override
    public void eat() {
        System.out.println("俄罗斯 喝 伏特加");
    }
}