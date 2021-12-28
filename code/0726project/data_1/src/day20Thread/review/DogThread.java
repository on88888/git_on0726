package day20Thread.review;

public class DogThread extends Thread {

    public DogThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {

            System.out.println(getName()+" ---> 跑 "+(i+1));
        }
    }
}
