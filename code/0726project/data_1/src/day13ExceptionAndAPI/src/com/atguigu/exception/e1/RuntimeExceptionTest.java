package day13ExceptionAndAPI.src.com.atguigu.exception.e1;

import org.junit.Test;

import java.util.Scanner;

/*
ArrayIndexOutOfBoundsException
NullPointerException
ClassCastException
ArithmeticException 算数异常
InputMismatchException
 */
class Animal{

}
class Dog extends Animal{

}
class Cat extends Animal{}
public class RuntimeExceptionTest {
    @Test
    public void test05(){
        Scanner in = new Scanner(System.in);

        System.out.println("请您输入一个数字");
        int num = in.nextInt();

        System.out.println("num = " + num);

    }



    @Test
    public void test04(){

        System.out.println(10 / 9);
        System.out.println(10 / 0);


    }

    @Test
    public void test03(){

        Animal a = new Cat();

        Dog d = (Dog)a;




    }
    @Test
    public void test02(){

        String s = null;
        System.out.println("s.hashCode() = " + s.hashCode());
    }

    @Test
    public void test01(){

        int[] arr = {10,20};
        System.out.println(arr[10]);

    }

}
