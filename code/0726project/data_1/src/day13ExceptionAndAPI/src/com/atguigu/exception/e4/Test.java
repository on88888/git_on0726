package day13ExceptionAndAPI.src.com.atguigu.exception.e4;

import java.io.FileNotFoundException;

/*
throws 给方法调用者提示

        throws
            在方法的声明处
            给方法调用者提示  会发生哪些异常
            可以有多个异常类型

        throw 在方法内
              抛出异常对象
              后面只能有一个异常对象

注意：
    如果抛出 编译时异常  必须使用throws声明
    如果抛出 运行时异常  既可以声明也可以不声明


 */
public class Test {
    @org.junit.Test
    public void test02(){

        try {
            show1();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void show1() throws FileNotFoundException {

        if(3>2){
            throw new FileNotFoundException("出错了");
        }

    }


    @org.junit.Test
    public void test01() {

        int[] arr = {10, 20, 30};
        show(arr);
    }
    private static void show(int[] arr) throws NullPointerException, ArrayIndexOutOfBoundsException {

        if (arr.length > 2) {

            throw new NullPointerException("数组长度太长了");
        }

        if (arr.length < 0) {
            throw new ArrayIndexOutOfBoundsException("数组下标越界");

        }


    }
}
