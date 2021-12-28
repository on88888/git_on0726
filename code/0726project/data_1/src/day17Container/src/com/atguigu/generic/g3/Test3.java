package day17Container.src.com.atguigu.generic.g3;

import org.junit.Test;

import java.util.ArrayList;
/*
泛型擦除：
        如果需要泛型 结果没有传入泛型  以Object类型为准

        局部变量的泛型 只存在于编译时
 */
public class Test3 {


    ArrayList<String> l1 = new ArrayList<>();

    public static void main(String[] args) {
        ArrayList<String> l2 = new ArrayList<>();

    }

    public static void cc(ArrayList<String> l3){


    }




    @Test
    public void test01(){

        ArrayList list = new ArrayList();

        list.add("aa");

        ArrayList<String> l = new ArrayList<>();


    }
}

class Ciricle implements Comparable<Ciricle>{

    int r;


    @Override
    public int compareTo(Ciricle o) {
        return 0;
    }
}
