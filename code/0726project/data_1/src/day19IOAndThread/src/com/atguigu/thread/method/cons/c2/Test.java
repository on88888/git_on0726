package day19IOAndThread.src.com.atguigu.thread.method.cons.c2;
/*
Thread(Runnable);
Thread(Runnable,name);
 */
public class Test {
    //jvm
    public static void main(String[] args) {

        CatRunnable catRunnable = new CatRunnable();
        Thread t1 = new Thread(catRunnable,"波斯猫");
        System.out.println("t1.getName() = " + t1.getName());
        t1.start();
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());

    }
}
