package day17Container.src.com.atguigu.generic.g1;

import java.util.ArrayList;
import java.util.Date;

/*
泛型：类型参数

   ArrayList<形参>
   ArrayList<实参> list

     E Element
     K Key
     V Value
     T Type


 */
public class Test3 {
    public static void main(String[] args) {


        ArrayList<String> list = new ArrayList<>();
        ArrayList<Integer> list1 = new ArrayList<>();


      //  sum(100,200);

        K<Integer> k = new K<>();

        K<Date> k1 = new K<>();


    }

    public static void sum(int a,int i2){

    }

}
class K<O>{

    O e;


}

interface Fly<P>{

    void show(P t);

}
