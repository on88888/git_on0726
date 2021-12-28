package day20Thread.notify.n1;
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

 */
public class Test {

    public static void main(String[] args) {

        //创建公共的吧台
        Bar bar = new Bar();


        CookerThread c1 = new CookerThread(bar);
        c1.setName("粤菜大厨");


        WaiterThread w1 = new WaiterThread(bar);
        w1.setName("靓女");
        w1.start();
        c1.start();
    }
}
