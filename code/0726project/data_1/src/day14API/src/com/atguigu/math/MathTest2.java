package day14API.src.com.atguigu.math;

import org.junit.Test;
import static java.lang.Math.*;
/*
静态导入 导入该类下所有的静态资源
 使用时可以省略 类名直接使用资源
 */
public class MathTest2 {

    @Test
    public void test01(){

        System.out.println("Math.PI = " + PI);

        System.out.println("Math.pow(3, 3) = " + pow(3, 3));
    }
}
