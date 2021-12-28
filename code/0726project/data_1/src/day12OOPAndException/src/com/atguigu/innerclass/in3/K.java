package day12OOPAndException.src.com.atguigu.innerclass.in3;

public class K {
    public static void main(String[] args) {
        // M num =z;
        M num = getNum();
        num.show();
    }
    public static M  getNum(){
        int num = 10;
      //  num = 20;
        //局部内部类
        class Z implements M{
            @Override
            public void show() {
                System.out.println(num);
            }
        }
         Z z = new Z();
        return z;
    }
}


