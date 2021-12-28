package day20Thread.synchronized1.s4;

public class TicketRunnable implements Runnable {
    int tickets = 100;


    @Override
    public void run() {

        while (true) {
            if (tickets <= 0) {
                break;
            }
            //卖票的方法
               sell();
        }
    }

    public synchronized void sell(){//this
        //当票数<=0 结束方法
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
        System.out.println(Thread.currentThread().getName() + " 卖了一张票 ，还有【 " + tickets + " 】张票");
    }
}
