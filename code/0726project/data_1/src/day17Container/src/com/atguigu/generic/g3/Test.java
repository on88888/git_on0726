package day17Container.src.com.atguigu.generic.g3;

import java.util.ArrayList;
import java.util.Collections;
/*
泛型方法： 在方法调用时 才能确定参数类型的方法

 */
public class Test{
    @org.junit.Test
    public void test01(){

        ArrayList<String> list = new ArrayList<>();


        list.add("李白");
    }

    @org.junit.Test
    public void test02(){

        show("a");
        show(10);


    }

    @org.junit.Test
    public void test03(){


        Collections c;

      //  Collections.addAll(, );


    }




    public static <T>void show(T t){

        System.out.println(t);

    }


}
