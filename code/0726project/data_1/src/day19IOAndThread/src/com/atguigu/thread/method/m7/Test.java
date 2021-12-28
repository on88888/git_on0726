package day19IOAndThread.src.com.atguigu.thread.method.m7;
/*


守护线程：守护其他线程 背景音乐
    当程序内只剩下守护线程时  守护线程会自动结束

在 线程启动之前 设置守护线程
 */
public class Test {
    public static void main(String[] args) {
        OtherThread o = new OtherThread();

        o.setDaemon(true);

        o.start();

        Thread.currentThread().setName("蔡旭坤");

        for (int i = 1; i <=20 ; i++) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" ---> "+i);

        }
    }
}
