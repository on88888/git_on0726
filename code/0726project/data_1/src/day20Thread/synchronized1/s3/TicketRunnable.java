package day20Thread.synchronized1.s3;

public class TicketRunnable implements Runnable {
    int tickets = 100;
    Object o = new Object();

    @Override
    public void run() {

        while (true) {
            synchronized (o) {
                if (tickets <= 0) {
                    break;
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


    }
}
