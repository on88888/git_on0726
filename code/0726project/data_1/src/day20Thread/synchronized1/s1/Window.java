package day20Thread.synchronized1.s1;

/*




 */
public class Window extends Thread {
    //火车票总数
    static int tickets = 100;

   static Object o = new Object();

    @Override
    public void run() {
        while (true) {

            synchronized (o){

            }

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
                System.out.println(getName() + "卖了一张票 还有 【 " + tickets + " 】张票");
            }


        }

    }
}
