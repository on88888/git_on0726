package day20Thread.notify.n2;

public class WaiterThread extends Thread {

    Bar bar;

    public WaiterThread(Bar bar) {
        this.bar = bar;
    }

    @Override
    public void run() {
        while (true){
           synchronized (bar){
               //取菜之前检查菜的数量
               if(bar.count<=Bar.MIN_NUM){
                   try {
                       bar.wait();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
               try {
                   Thread.sleep(10);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               //取菜
               bar.count--;
               System.out.println(getName()+"取走了一道菜，现在吧台上有【 "+bar.count+" 】道菜");

               //取走一道菜后 通知厨师做菜
               bar.notify();

           }
        }
    }
}
