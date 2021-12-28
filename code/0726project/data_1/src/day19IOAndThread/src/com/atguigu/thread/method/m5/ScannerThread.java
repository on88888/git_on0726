package day19IOAndThread.src.com.atguigu.thread.method.m5;

import java.util.Scanner;

public class ScannerThread extends Thread {

    Scanner in = new Scanner(System.in);

    @Override
    public void run() {

        while (true){
            System.out.println("请问您结束当前线程吗？ Y 结束  N 结束");
            String choose = in.next();

            if(choose.equalsIgnoreCase("Y")){
                break;
            }
        }
    }
}
