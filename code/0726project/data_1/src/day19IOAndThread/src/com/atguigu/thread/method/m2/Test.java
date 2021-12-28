package day19IOAndThread.src.com.atguigu.thread.method.m2;

public class Test {
    public static void main(String[] args) {
        A a = new A();
        a.start();
    }
}

class A extends Thread {

    @Override
    public void run() {
        for (int i = 3; i > 0; i--) {
            System.out.println(i);
            try {
                //此处只能try..catch() 因为 子类重写父类方法 不能抛出比父类更大的异常
                Thread.sleep(1000);//1m = 1000ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("跑.....");
    }
}