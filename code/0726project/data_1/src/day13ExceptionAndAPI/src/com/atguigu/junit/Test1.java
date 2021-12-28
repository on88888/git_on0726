package day13ExceptionAndAPI.src.com.atguigu.junit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

/*
单元测试如何使用

注意：
    1.单元测试方法 必须是 公共的类

    2.单元测试方法必须是 公共的方法

    3.方法名无要求

    4.单元测试类 不要混杂其他内容

      @Before 运行单元测试方法前 会执行的内容
      @After  运行单元测试方法后 会执行的内容
       面向切面编程
 */
public class Test1 {

    @Before
    public void t(){
        System.out.println("this is before");
    }

    @After
    public void a(){
        System.out.println("this is After");
    }

    @Test
    public   void test01(){
        System.out.println(1111);
    }
    @Test
    public void test02(){
        System.out.println(222);
    }
    @Test
    public void test03(){
        System.out.println(3333);
        Scanner in = new Scanner(System.in);

        String next = in.next();
        System.out.println("next = " + next);
    }
}
