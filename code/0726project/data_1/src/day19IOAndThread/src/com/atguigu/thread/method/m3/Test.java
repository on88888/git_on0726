package day19IOAndThread.src.com.atguigu.thread.method.m3;
/*
Thread.sleep();  由运行 ---> 阻塞
Thread.yild();   由运行 ---> 就绪
 */
public class Test {
    public static void main(String[] args) {
        RabbitThread r1 = new RabbitThread();
        r1.setName("大白兔");
        DogThread  dogThread = new DogThread();
        dogThread.setName("旺财");
        dogThread.start();
        r1.start();
    }
}
