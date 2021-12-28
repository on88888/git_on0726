package day12OOPAndException.src.com.atguigu.innerclass.in4.in3;
class SuperMan implements Fly{
    @Override
    public void fly() {
        System.out.println("超人飞");
    }
}
public class Test2 {
    public static void main(String[] args) {

        /*SuperMan superMan = new SuperMan();
        showFly(superMan);*/

    /*    Fly fly = new Fly() {
            @Override
            public void fly() {
                System.out.println("小鸟飞.....");
            }
        };
         showFly(fly);
        */
    showFly(new Fly() {
        @Override
        public void fly() {
            System.out.println("小鸟飞........");
        }
    });


    }


    public static void showFly(Fly f){//Fly f = superMan;

        f.fly();
    }
}

interface Fly{
    void fly();
}