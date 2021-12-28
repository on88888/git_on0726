package day15APIAndList.src.com.atguigu.enum1.jdk5a;

import org.junit.Test;

public class SwitchTest {

    @Test
    public void test03(){

        Integer i1 = new Integer("10");

        switch (i1){
            case 10:
                System.out.println("this is 10");
                break;

        }





    }

    @Test
    public void test02(){

        Season spring = Season.SPRING;
        // byte short int char String enum
        switch (spring){

            case AUTUMN:
                System.out.println("秋天");
                break;
            case SPRING:
                System.out.println("春天");
                break;
            case SUMMER:
                System.out.println("夏天");
                break;
            case WINTER:
                System.out.println("冬天");
                break;
        }


    }



    @Test
    public void test01(){
        Gender man = Gender.WOMAN;

        switch (man){
            case MAN:
                System.out.println("男生");
                break;

            case WOMAN:
                System.out.println("女生");
                break;
        }


    }
}
