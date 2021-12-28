package day19IOAndThread.src.com.atguigu.thread.method.m5;

public class Test {
    public static void main(String[] args) throws InterruptedException {

        //创建线程对象
        ScannerThread s = new ScannerThread();
        //启动线程
        s.start();

        for (int i = 1; i <= 10 ; i++) {

            System.out.println(Thread.currentThread().getName()+" --> "+i);
            Thread.sleep(500);
            //当 i=5时  自定义线程 加塞
            if(i==5){
                s.join();
            }
        }

    }
}
