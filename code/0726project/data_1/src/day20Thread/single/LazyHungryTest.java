package day20Thread.single;

import org.junit.Test;

public class LazyHungryTest {

    @Test
    public void test01(){
        LazyHungry l1 = LazyHungry.getInstance();
        LazyHungry l2 = LazyHungry.getInstance();
        System.out.println(l1 == l2);
    }
    @Test
    public void test02(){

    }
}

class LazyHungry1{

    public static LazyHungry1 intance;

    private LazyHungry1(){}


   /* public static synchronized LazyHungry1 getInstance(){

        if(intance==null){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            intance = new LazyHungry1();
        }
        return intance;
    }*/

    public static  LazyHungry1 getInstance(){

        if(intance==null){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (LazyHungry1.class){
                if(intance==null){

                    intance = new LazyHungry1();
                }
            }

        }
        return intance;
    }
}


class LazyHungry{

    public static LazyHungry intance;

    private LazyHungry(){}


    public static LazyHungry getInstance(){

        if(intance==null){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            intance = new LazyHungry();
        }
        return intance;
    }

}
