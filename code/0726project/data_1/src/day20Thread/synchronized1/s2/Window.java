package day20Thread.synchronized1.s2;

public class Window extends Thread {
    //火车票总数
    static int tickets = 100;
    @Override
    public void run() {
        while (true) {
                if (tickets <= 0) {
                    break;
                }
                //调用方法卖票
                sell();
        }
    }
    /*

    1.为什么不能使用普通方法？
        因为创建了三个窗口对象  所以this 不一样 同步监视器对象不一样 无法实现同步

     2.为什么使用静态方法？
           因为创建的三个窗口对象 运行时类型 是相同的  Window.class 同步监视器对象是一样的  可以实现同步

     */
    public static synchronized void s(){}

    public static synchronized void sell(){
        if(tickets<=0){
            return;
        }
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //卖票
        tickets--;
        System.out.println(Thread.currentThread().getName() + "卖了一张票 还有 【 " + tickets + " 】张票");


    }
}