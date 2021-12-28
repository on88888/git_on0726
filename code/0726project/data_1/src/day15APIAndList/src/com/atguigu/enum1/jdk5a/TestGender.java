package day15APIAndList.src.com.atguigu.enum1.jdk5a;

import org.junit.Test;

public class TestGender {


    @Test
    public void test03(){

        Gender woman = Gender.valueOf("WOMAN");
        System.out.println("woman = " + woman);


    }


    @Test
    public void test02(){
        //获取所有的枚举对象
        Gender[] values = Gender.values();


        for (Gender value : values) {

            System.out.println("value = " + value);
        }


    }

    @Test
    public void test01(){
        Gender man = Gender.MAN;

        Gender woman = Gender.WOMAN;


        man.walk();
        System.out.println("----");
        woman.walk();


    }
}
