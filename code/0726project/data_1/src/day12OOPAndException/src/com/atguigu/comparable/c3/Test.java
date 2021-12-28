package day12OOPAndException.src.com.atguigu.comparable.c3;


public class Test {
    public static void main(String[] args) {

        //创建4个小猫对象
        Cat c1 = new Cat("波斯猫", 5, 0.4);
        Cat c2 = new Cat("黑猫", 2, 0.3);
        Cat c3 = new Cat("白猫", 3, 0.5);
        Cat c4 = new Cat("加菲猫", 4, 0.7);
        Cat c5 = new Cat("招财猫", 1, 0.2);

        Cat[] cs = {c1,c2,c3,c4,c5};
        showCat(cs, "排序前 = ");
        //排序操作

        for (int i = 0; i < cs.length-1; i++) {
            for (int j = 0; j < cs.length-i-1; j++) {
                if(cs[j].compareTo(cs[j+1])>0){
                    Cat temp = cs[j];
                    cs[j]=cs[j+1];
                    cs[j+1]=temp;


                }


            }

        }







        showCat(cs, "排序后 = ");

    }



    public static void showCat(Cat[] cs, String s) {
        System.out.println();
        for (Cat c : cs) {
            System.out.println(s + c);
        }
    }
}
