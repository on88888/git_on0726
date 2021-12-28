package day19IOAndThread.src.com.atguigu.thread.create.c3;

public class Test1 {
    public static void main(String[] args) {
        //1.创建接口实现类对象
        CatRunnable catRunnable = new CatRunnable();
        //2.将此对象作为参数传到Thread内

        Thread t1 = new Thread(catRunnable);
        Thread t2 = new Thread(catRunnable);

        t1.start();
        t2.start();
    }

}

class CatRunnable implements Runnable{
    int i = 66;

    @Override
    public void run() {

        System.out.println("小猫跑....");
    }
}
