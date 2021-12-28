package day20Thread.notify.n3;

/*

吧台： 只能放10道菜

厨师线程  做菜
    当吧台上已经有了10道 菜  厨师线程 等待

    当厨师做好菜时 就可以 通知 服务员端菜


服务员线程 端菜
    当吧台上没有菜时  等待  通知厨师做菜

问题：
    1.出现了 菜的数量过大或者过小的问题

    2.做菜的时候  服务员不能取菜

wait():
        1.让线程进入阻塞状态
        2.会释放锁
        3.方法的调用者 必须是同步监视器对象
           必须在 同步代码块或者是同步方法内

 */
public class Test {

    public static void main(String[] args) {

        //创建公共的吧台
        Bar bar = new Bar();
        CookerThread c1 = new CookerThread(bar);
        c1.setName("粤菜大厨 ");
        CookerThread c2 = new CookerThread(bar);
        c2.setName("湘菜大厨 ");


        WaiterThread w1 = new WaiterThread(bar);
        w1.setName("靓女 ");
        WaiterThread w2 = new WaiterThread(bar);
        w2.setName("靓仔 ");

        w1.start();
        w2.start();
        c1.start();
        c2.start();
    }
}
