package day19IOAndThread.src.com.atguigu.thread.method.m6;

public class DogThread extends Thread {


    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {

            if(i==50){
                //打标记
                this.interrupt();
            }
                //判断是否已打标记
            if(this.isInterrupted()){
                break;
            }
            /*if(i==50){
                stop();
            }*/
            System.out.println(this.getName()+" 跑 " +i);

        }
    }
}
