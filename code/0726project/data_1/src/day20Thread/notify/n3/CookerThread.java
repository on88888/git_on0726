package day20Thread.notify.n3;

public class CookerThread extends Thread {
    Bar bar;
    public CookerThread(Bar bar) {
        this.bar = bar;
    }
    @Override
    public void run() {
        while (true){
            synchronized (bar){
                //做菜之前检查菜的数量
                //唤醒后继续校验 是否满足睡眠的规则
                while (bar.count>= Bar.MAX_NUM){
                    //厨师线程等待
                    try {
                        bar.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //做菜
                bar.count++;
                System.out.println(getName()+"做了一道菜，现在吧台上有 【"+bar.count+" 】道菜");
                //做好一道菜就可以通知 服务员取菜
                bar.notifyAll();
            }
        }
    }
}
