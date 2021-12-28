package day19IOAndThread.src.com.atguigu.thread.method.m4;
/*
isAlive: 判断线程是否处于活动状态

 */
public class Test {
    //jvm
    public static void main(String[] args) throws InterruptedException {

        DogThread d = new DogThread();
        System.out.println("d.isAlive()  1 = " + d.isAlive());
        d.start();
        System.out.println("d.isAlive()  2 = " + d.isAlive());
        //主线程睡5s
        Thread.sleep(5000);
        System.out.println("d.isAlive()  3 = " + d.isAlive());


    }
}
