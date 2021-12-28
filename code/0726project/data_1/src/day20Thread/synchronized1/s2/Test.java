package day20Thread.synchronized1.s2;

/*
使用三个窗口卖 火车票  100张

多条线程 共同操作 共享数据  会引发线程安全问题
如何解决线程安全问题： 保证 在同一时刻 只能有一个线程在操作共享数据

    1.同步代码块
            synchronized(同步监视器对象){
                操作共享数据的代码

            }

       注意：
        1.同步监视器对象：只要是对象就可以
        2.实现线程同步 那么需要所有的线程使用同一个同步监视器对象
        3.如果一个线程进入了同步代码块 那么其他线程 不仅不能进入此代码块也不能进入 拥有同一个同步监视器对象的其他同步代码块

    2.同步方法
            在返回值前 + synchronized就是同步方法

            非静态方法 同步监视器对象是 this

            静态方法 同步监视器对象是 运行时类型 Class
                获取运行时类型的方式
                        getClass()
                        类名.class
       注意：
            如果一个线程进入到同步方法 那么其他线程不仅不能进入此同步方法 也无法进入 拥有同一个同步监视器对象的其他同步方法
 */
public class Test {
    public static void main(String[] args) {

        Window w1 = new Window();
        w1.setName("窗口一");
        Window w2 = new Window();
        w2.setName("窗口二");
        Window w3 = new Window();
        w3.setName("窗口三");

        w1.start();
        w2.start();
        w3.start();


    }
}
