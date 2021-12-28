package day12OOPAndException.src.com.atguigu.innerclass.in4.i1;
/*
匿名内部类：没有名字的内部类
        简化代码

   单创建完毕匿名内部类两个作用
        1.完成了该类匿名子类创建
        2.完成了该类匿名子类对象创建


 */
public class Test {
    public static void main(String[] args) {


       // DogRunnable d = new DogRunnable();
     /*   Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("小狗跑");
            }
        };*/
        Thread t = new Thread(()-> System.out.println("小狗跑"));

        t.start();

    }
}
/*class DogRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("小狗跑.....");
    }
}*/
