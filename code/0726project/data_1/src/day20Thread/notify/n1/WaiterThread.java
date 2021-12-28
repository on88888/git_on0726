package day20Thread.notify.n1;

public class WaiterThread extends Thread {

    Bar bar;

    public WaiterThread(Bar bar) {
        this.bar = bar;
    }

    @Override
    public void run() {
        while (true){

            //取菜
            bar.count--;

            System.out.println(getName()+"取走了一道菜，现在吧台上有【 "+bar.count+" 】道菜");
        }
    }
}
