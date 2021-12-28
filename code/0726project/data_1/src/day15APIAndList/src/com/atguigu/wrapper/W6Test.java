package day15APIAndList.src.com.atguigu.wrapper;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static jdk.nashorn.internal.codegen.OptimisticTypesPersistence.load;

public class W6Test {

    @Test
    public void test01(){

        Integer i1 = 10;
        //自动拆箱
        double d=i1;


        /*
        i1:是Integer 类型

        d1: Double 类型
          两个不同的引用数据类型
        Double d1 = i1;
         */
    }
    @Test
    public void test02() throws IOException {
        Integer i1 = 10;
        Integer integer = new Integer(20);
        System.out.println("前 i1 = " + i1);
        chang(i1);
        System.out.println("后 i1 = " + i1);
    }
    private void chang(Integer i1) {
        i1 = 30;
        System.out.println("中 i1 = " + i1);
    }
}
