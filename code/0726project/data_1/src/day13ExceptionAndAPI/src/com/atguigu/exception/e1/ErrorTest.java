package day13ExceptionAndAPI.src.com.atguigu.exception.e1;

import org.junit.Test;

import java.util.Arrays;

/*
StackOverflowError
OutOfMemoryError
 */
public class ErrorTest {

    @Test
    public void test02(){
       // OutOfMemoryError;
        int [] arr =new int[1_0000_0000_0];

        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));


    }

    @Test
    public void test01(){
      //  show();
    }
    private void show() {
        show();
    }
}
