package day12OOPAndException.src.com.atguigu.innerclass.in2;

public class Test {
    public static void main(String[] args) {
      /*  Outer o = new Outer();
        o.outerMethod1();*/

      Outer.Inner.innerMethod();

      Outer.Inner in = new Outer.Inner();

    }
}
