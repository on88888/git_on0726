package day19IOAndThread.src.com.atguigu.thread.create.c4;

public class Test1 {

    public static void main(String[] args) {

        //匿名内部类
        Thread t1 = new Thread() {
            @Override
            public void run() {
                System.out.println("小狗跑....");
            }
        };
        t1.start();

        //匿名内部类
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("小猫跑.....");
            }
        });

        t2.start();
    }
}
