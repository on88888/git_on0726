package day15APIAndList.src.com.atguigu.enum1.jdk5a;

import org.junit.Test;

public class TestSeason {
    @Test
    public void test01(){
        Season s1 = Season.SPRING;
        Season s2 = Season.SPRING;

        System.out.println(s1==s2);



    }
    @Test
    public void test02(){
        Enum e;
        Season s1 = Season.SUMMER;

        System.out.println("s1 = " + s1);


    }

    @Test
    public void test03(){

        Season[] values = Season.values();

        for (Season value : values) {
            System.out.println("value = " + value);
        }


    }

    @Test
    public void test04(){

        Season spring = Season.valueOf("AUTUMN");
        System.out.println("spring = " + spring);


        int ordinal = spring.ordinal();

        System.out.println("ordinal = " + ordinal);

    }
}
