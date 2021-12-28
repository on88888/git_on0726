package day11OOP.src.com.atguigu.review;
/*
新建一个乐器类 Instrument
内部有一个演奏的方法 play();

 新建子类 Piano 重写此方法 play() 输出 弹钢琴
 新建子类 ErHu 重写此方法 play() 输出 拉二胡

 新建子类 Guitar 重写此方法 play() 输出 弹吉他


新建一个人类 ， 在内部定义一个方法 在方法内可以演奏所有的乐器
    弹钢琴
    拉二胡
    弹吉他
新建测试类 创建人类对象 调用方法测试

 */
public class Test {
    public static void main(String[] args) {

        Person p = new Person();

        Piano p1 = new Piano();
        p.tan(p1);
        System.out.println("----------------");
        p.tan(new ErHu());

    }

}
