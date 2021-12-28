package day07OOP.src.com.atguigu.exer3;

public class Test {
    public static void main(String[] args) {
        //创建员工对象
        Employee e1 = new Employee();
        e1.name = "张三";

        MyDate my1 = new MyDate();
        my1.year = 2020;
        my1.month = 3;
        my1.day = 9;
        e1.birthday = my1;
        System.out.println("名字是："+e1.name+",生日是："+e1.birthday.year+"年，"+e1.birthday.month+"月，"+e1.birthday.day+"日");
        //创建员工对象2
        Employee e2 = new Employee();
        e2.name="李四";

        e2.birthday=new MyDate();

        e2.birthday.year=1998;
        e2.birthday.month=9;
        e2.birthday.day=17;
        System.out.println("名字是："+e2.name+",生日是："+e2.birthday.year+"年，"+e2.birthday.month+"月，"+e2.birthday.day+"日");
    }
}
