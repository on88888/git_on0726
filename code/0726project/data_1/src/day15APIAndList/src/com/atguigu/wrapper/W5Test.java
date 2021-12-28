package day15APIAndList.src.com.atguigu.wrapper;

import org.junit.Test;
/*

Byte: [-128,127]
Short: [-128,127]
Integer: 缓冲数组 [-128,127]
Long: [-128,127]
Float:  没有缓存区
Double: 没有缓存区
Boolean true  false


Character
      [0,127]


 */
public class W5Test {
    @Test
    public void test02(){

        Character.valueOf('a');


        byte b  =10;
        Byte aByte = Byte.valueOf(b);


        short s = 10;
        Short.valueOf(s);

        Long.valueOf(20L);


        Double.valueOf(3.14);

        Boolean.valueOf(true);

    }

    @Test
    public void test01(){

        Integer i1 = Integer.valueOf(10);
        Integer i2 = Integer.valueOf(10);

        Integer i3 = Integer.valueOf(100);
        Integer i4 = Integer.valueOf(100);

        Integer i5 = Integer.valueOf(1000);
        Integer i6 = Integer.valueOf(1000);

        System.out.println("i1==i2 = " + (i1 == i2));
        System.out.println("i3==i4 = " + (i3 == i4));
        System.out.println("i5==i6 = " + (i5 == i6));

    }
}
