package day19IOAndThread.src.com.atguigu.thread.method.m7;

public class OtherThread extends Thread {

    @Override
    public void run() {
        while (true){
            System.out.println("蔡旭坤 我好喜欢你 。。。。。。。。。。。");
            /*try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
    }
}
