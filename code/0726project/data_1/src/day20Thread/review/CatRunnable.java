package day20Thread.review;

public class CatRunnable implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {

            System.out.println(Thread.currentThread().getName()+" ---> 跑"+(i+1));
        }
    }
}
