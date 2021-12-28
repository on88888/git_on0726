package day19IOAndThread.src.com.atguigu.thread.create.c3;
/*

1. 实现接口 代码复杂
  继承方式更加简单

2. 实现方式 更容易实现 数据共享
 */
public class Test {

    public static void main(String[] args) {

        DogThread d = new DogThread();

        d.start();


        DogThread d1 = new DogThread();

        d1.start();

    }
}
