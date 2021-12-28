package day20Thread.review;
/*


 */
public class Test {
    public static void main(String[] args) {

        DogThread d1 = new DogThread("旺财");

        d1.start();

        CatRunnable catRunnable = new CatRunnable();

        Thread t1 = new Thread(catRunnable,"波斯猫");

        t1.start();

    }
}
