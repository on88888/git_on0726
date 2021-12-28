package day19IOAndThread.src.com.atguigu.thread.method.m1;
/*

设置线程的优先级
        优先级的范围  1~10
        public final static int MAX_PRIORITY = 10;
        public final static int MIN_PRIORITY = 1;
        //如果没有设置优先级 默认为 5
        public final static int NORM_PRIORITY = 5;
 */
public class Test {

    public static void main(String[] args) {

        DogThread d1 = new DogThread();
        d1.setPriority(10);
        d1.setName("小黄狗");
        System.out.println("d1.getPriority() = " + d1.getPriority());

        CatRunnable catRunnable = new CatRunnable();
        Thread t2 = new Thread(catRunnable, "大黑猫");
        System.out.println("t2.getPriority() = " + t2.getPriority());

        t2.start();
        d1.start();
    }
}
