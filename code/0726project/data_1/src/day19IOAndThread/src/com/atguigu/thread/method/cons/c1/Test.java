package day19IOAndThread.src.com.atguigu.thread.method.cons.c1;

/*
Thread();
Thread(String name);

 */

public class Test {
    public static void main(String[] args) {
        RabbitThread r1 = new RabbitThread("小白兔");
      //  r1.setName("大白兔");
        System.out.println("r1.getName() = " + r1.getName());
        r1.start();
      /*  RabbitThread r2 = new RabbitThread("小黑兔");
        r2.start();*/
    }
}
