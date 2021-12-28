package day11OOP.src.com.atguigu.poly.exer1;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        SalaryEmployee s1 = new SalaryEmployee("张三-正式工", new MyDate(2000, 2, 9), 20000, 20, 2);
        HourEmployee h1 = new HourEmployee("李四-小时工", new MyDate(2002, 3, 9), 9, 30);
        Manager m1 = new Manager("王五-经理", new MyDate(1998, 3, 9), 25000, 22, 0, 0.3);
        Employee[] es = {s1,h1,m1};

        //创建键盘输入对象
        Scanner in = new Scanner(System.in);
        System.out.println("请输入您的月份");
        int month = in.nextInt();

        for (Employee e : es) {

            System.out.println(e.getInfo());
            //经理 是正式工的孩子 因此 也是正式工
            if(e instanceof SalaryEmployee){
                //判断是否为本月生日
                if(e.getBirthday().month==month){
                    System.out.println("祝 "+e.getName()+" 生日快乐,请来前台领取生日礼物");

                }


            }else{
                //将小时工强制转换
                HourEmployee h = (HourEmployee)e;
                h.leave();
            }
        }






    }
}
