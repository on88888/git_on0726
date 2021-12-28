package day19IOAndThread.src.com.atguigu.thread.method.m5;
/*
join():
    强行插队

join(毫秒数)
    指定强行插队的时间  时间到了 其他线程可以执行


 */
public class JoinTest {

    public static void main(String[] args) throws InterruptedException {


        PersonThread p = new PersonThread();
        p.setName("领导....");

        p.start();

        for (int i = 0; i < 20; i++) {

            if(i==10){
             //   p.join();
                p.join(5000);
            }
            System.out.println("main 在执行....."+(i+1));

        }
    }
}
