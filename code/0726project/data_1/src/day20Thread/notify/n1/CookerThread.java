package day20Thread.notify.n1;

public class CookerThread extends Thread {
    Bar bar;

    public CookerThread(Bar bar) {
        this.bar = bar;
    }

    @Override
    public void run() {

        while (true){

            //做菜
            bar.count++;

            System.out.println(getName()+"做了一道菜，现在吧台上有 【"+bar.count+" 】道菜");

        }
    }
}
