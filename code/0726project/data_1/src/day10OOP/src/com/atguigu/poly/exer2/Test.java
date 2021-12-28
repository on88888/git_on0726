package day10OOP.src.com.atguigu.poly.exer2;

public class Test {
    public static void main(String[] args) {

        SalaryEmployee s1 = new SalaryEmployee("张三-正式工", 22000, 20, 2);
        HourEmployee h1 = new HourEmployee("李四-小时工", 8, 20);
        Manager m1 = new Manager("王五-经理", 20000, 22, 0, 0.5);

        Employee [] es = {s1,h1,m1};

        double sum = 0;
        for (Employee e : es) {

            String info = e.getInfo();

            sum+=e.earning();

            System.out.println(info);
        }

        System.out.println("sum = " + sum);
    }
}
